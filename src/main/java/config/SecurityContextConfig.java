package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity  // If this is not included, following error occurs:
// org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'securityContextConfig': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire method: public void org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter.setObjectPostProcessor(org.springframework.security.config.annotation.ObjectPostProcessor); nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.springframework.security.config.annotation.ObjectPostProcessor] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
public class SecurityContextConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user").password("password").roles("USER").and()
            .withUser("admin").password("adminpassword").roles("USER", "ADMIN");
    }

    @Configuration
    @Order(1)
    public static class DemoWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/demo")
                .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(2)
    public static class AdminWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/admin/**")
                .authorizeRequests()
                .anyRequest().hasRole("ADMIN")
                .and()
                .httpBasic();
        }
    }

    @Configuration
    @Order(3)
    public static class DefaultWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin();
        }
    }

}
