package company.ggi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ismail ELFAQIR on 25/04/2017.
 */

@Configuration
public class ResourceServersConfig {


    @Bean
    protected ResourceServerConfiguration userResources() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Collections.<ResourceServerConfigurer>singletonList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                resources.resourceId("user-resources");
            }

            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.csrf().disable();
                http.httpBasic().disable();
                http.antMatcher("/**").authorizeRequests().anyRequest()
                        .access("#oauth2.hasAnyScope('read', 'write')");
            }
        }));
        resource.setOrder(3);
        return resource;
    }

    @Bean
    protected ResourceServerConfiguration adminResources() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Collections.<ResourceServerConfigurer>singletonList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                resources.resourceId("admin-resources");
            }

            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.antMatcher("/admin/**").authorizeRequests().anyRequest()
                        .access("#oauth2.hasScope('administration')");
            }
        }));
        resource.setOrder(2);
        return resource;
    }

    @Bean
    protected ResourceServerConfiguration publicResources() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Collections.<ResourceServerConfigurer>singletonList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                resources.resourceId("public-resources");
            }

            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.csrf().disable();
                http.httpBasic().disable();
                http.antMatcher("/test")
                        .authorizeRequests().anyRequest()
                        .permitAll();
            }
        }));
        resource.setOrder(1);
        return resource;
    }

}
