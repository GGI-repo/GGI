package company.ggi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by Ismail ELFAQIR on 25/04/2017.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenStore tokenStore;

    @Value("${ggi.oauth.tokenTimeout:3600}")
    private int expiration;

    @Value("${ggi.oauth.usersClientId}")
    private String usersClientId;

    @Value("${ggi.oauth.usersPassword}")
    private String usersPassword;

    @Value("${ggi.oauth.adminsClientId}")
    private String adminsClientId;

    @Value("${ggi.oauth.adminsPassword}")
    private String adminsPassword;

    public static final String PUBLIC_RESOURCES = "public-resources";
    public static final String ADMIN_RESOURCES = "admin-resources";
    public static final String USER_RESOURCES = "user-resources";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
        configurer.authenticationManager(authenticationManager);
        configurer.userDetailsService(userDetailsService);
        configurer.tokenStore(tokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(usersClientId)
                .secret(usersPassword)
                .accessTokenValiditySeconds(expiration)
                .authorities()
                .scopes("read", "write")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .resourceIds(USER_RESOURCES, PUBLIC_RESOURCES)
                .and()
                .withClient(adminsClientId)
                .secret(adminsPassword)
                .accessTokenValiditySeconds(expiration)
                .authorities()
                .scopes("read", "write", "trust", "administration")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .resourceIds(ADMIN_RESOURCES, USER_RESOURCES, PUBLIC_RESOURCES);
    }
}
