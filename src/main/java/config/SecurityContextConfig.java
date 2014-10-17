package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
            .withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated() // Ensures that any request to our application requires the user to be authenticated
                    .and()
                .formLogin() // Allows users to authenticate with form based login
                    .and()
                .httpBasic() // Allows users to authenticate with HTTP Basic authentication
        // in other words, if user submits credentials using basic auth, fulfill the request
        // otherwise show basic authentication dialog
        // If user goes to  http://localhost:8080/app/login, show form based login screen
        ;
    }
}
