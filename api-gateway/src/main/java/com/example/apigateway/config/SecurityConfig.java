package com.example.apigateway.config;

import com.example.apigateway.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomUserService userService;

    @Bean("encoder")
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(
                        "/signup/**",
                        "/login/**",
                        "/user/identify/**",
                        "/post/list",
                        "/comment/list",
                        "/v2/api-docs",
                        "/swagger-ui.html**",
                        "/webjars/**",
                        "/swagger",
                        "/swagger-ui**",
                        "/swagger-resources",
                        "/comment/v2/**",
                        "/post/v2/**",
                        "/user/v2/**",
                        "/post/*/comment").permitAll()
                .antMatchers(
                        "/user/list",
                        "/user/comment",
                        "/profile/**",
                        "/post/**",
                        "/purge/**",
                        "/comment/**").authenticated()
                .antMatchers("/role/**").hasRole("ADMIN")
                .and()
                .cors()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
