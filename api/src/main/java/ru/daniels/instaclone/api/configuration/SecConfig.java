package ru.daniels.instaclone.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.daniels.instaclone.api.security.JWTTokenFilter;
import ru.daniels.instaclone.api.security.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailService userDetailService;
    private final JWTTokenFilter jwtTokenFilter;

    public SecConfig(UserDetailService userDetailService, JWTTokenFilter jwtTokenFilter) {
        this.userDetailService = userDetailService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.GET,"/media/**")
                .antMatchers(HttpMethod.GET,"/script/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.printf("Config Security {%s}\n", http.toString());
        http.csrf().disable().exceptionHandling();
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/api/login").permitAll()
                    .antMatchers("/registration", "/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
