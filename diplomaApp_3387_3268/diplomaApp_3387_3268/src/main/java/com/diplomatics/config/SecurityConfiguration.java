package com.diplomatics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        private UserDetailsService service;

        @Autowired
        public void WebSecurityConfig(UserDetailsService service) {
            this.service = service;
        }

        @Autowired
        UserDetailsService userDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/diploma").authenticated()
                    .antMatchers("/myprofile").authenticated()
                    .antMatchers("/diploma/info/**").authenticated()
                    .antMatchers("/diploma/**").hasAuthority("PROF")
                    .antMatchers("/applications/**").authenticated()
                    .antMatchers(HttpMethod.GET, "/users/**").authenticated()
                    .antMatchers("/users/exist/*").permitAll()
                    .and().formLogin()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .and()
                    .csrf().disable().cors();
        }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
