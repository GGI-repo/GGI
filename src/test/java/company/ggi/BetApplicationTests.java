package company.ggi;

import company.ggi.dao.UserDao;
import company.ggi.model.User;
import company.ggi.service.HealthCheckService;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.net.URL;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BetApplicationTests {

	@LocalServerPort
	private int port;
	private URL base;

	@Autowired
	private TestRestTemplate template;

	@Autowired
	WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;

	private MockMvc mvc;

	private User user;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/healthcheck");
		this.mvc = MockMvcBuilders.webAppContextSetup(context)
				.addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void testHealtCheck() throws Exception{
		HealthCheckService healthCheck = this.template.getForObject(base.toString(), HealthCheckService.class);
		assertThat(healthCheck.getVersion()).isNotNull();
		assertThat(healthCheck.getMessage()).isNotNull();
	}

	@Test
	public void testUnauthorizedAccess() throws Exception{
		mvc.perform(get("/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.error", is("unauthorized")));
	}
/*
	@Test
	public void testAuthorizedAccess() throws Exception{
		String accessToken = getAccessToken("user", "password");

		// @formatter:off
		mvc.perform(get("/" + "?access_token="+accessToken))
				.andExpect(status().isNotFound());
	}

	private String getAccessToken(String username, String password) throws Exception {
		user = new User("test", "user", "test", "test@test.com", new Date());
		user.setPassword("$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri");
		user.setEnabled(true);
		user.setCredit(1005d);
		user = userDao.save(user);
		String authorization = "Basic ";
		System.out.println("===========>" + user.getUserName() + " | "+user.getPassword());
		String content = mvc
				.perform(
						post("/oauth/token")
								.header("authorization", authorization)
								.param("grant_type", "password")
								.param("username", user.getUserName())
								.param("password", "password"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
				.andExpect(jsonPath("$.refresh_token", is(notNullValue())))
				.andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
				.andExpect(jsonPath("$.scope", is(equalTo("read write"))))
				.andReturn().getResponse().getContentAsString();

		System.out.println("============>" + content.substring(17, 53));
		return content.substring(17, 53);
	}*/
}
