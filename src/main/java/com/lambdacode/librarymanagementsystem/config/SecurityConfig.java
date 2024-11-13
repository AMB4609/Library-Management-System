package com.lambdacode.librarymanagementsystem.config;

//import com.lambdacode.librarymanagementsystem.service.loginImpl.LoginServiceImpl;
import com.lambdacode.librarymanagementsystem.filter.JwtFilter;
import com.lambdacode.librarymanagementsystem.service.MyUserDetailsServiceImpl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
//this class bypasses all the default security chain and make a custom chain which we can manually write what filters we want to add
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(customizer -> customizer.disable()) // disabling csrf
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("api/register/registerUser")
//                        .requestMatchers("api/login/loginUser")
                        .permitAll()
                        .requestMatchers("api/register/registerStaff")
                        .permitAll()
                        .requestMatchers("api/login/loginUser")
//                        .requestMatchers("api/register/**")
                        .permitAll()
                         // this permit all the requestMatcher's request which means these pages only get access without spring security Interference
                        .anyRequest().authenticated())//no one is able to access the page without authentication except requestMatchers pages
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        };
//            @Bean
//            public UserDetailsService userDetailsService(){
//                Object User;
//                UserDetails user1 = User
//                        . withDefaultPasswardEncoder()
//                        . username ("kiran")
//                        .password("k@123")
//                        .roles ("USER")
//                        .build();
//                return new InMemoryUserDetailsManager(user1);
//    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}