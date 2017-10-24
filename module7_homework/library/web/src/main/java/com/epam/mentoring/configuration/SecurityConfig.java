package com.epam.mentoring.configuration;

import com.epam.mentoring.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/**", "/swagger-ui.html*").hasAuthority(UserRole.ADMIN.toString())
                .antMatchers("/*inventor*", "/create-book", "/create-author")
                    .hasAnyAuthority(UserRole.ADMIN.toString(), UserRole.LIBRARIAN.toString())
                .anyRequest().fullyAuthenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .successForwardUrl("/")
                .usernameParameter("emailAddress")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                //.deleteCookies("remember-me")
                .logoutSuccessUrl("/home")
                .permitAll()
                //.and().rememberMe()
                .and()
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

}
