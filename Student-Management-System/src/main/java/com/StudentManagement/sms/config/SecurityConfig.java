package com.StudentManagement.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Autowired
    public UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrf) -> csrf.disable()).authorizeHttpRequests(
                        authorize -> {

//                    authorize.anyRequest().authenticated();
                            authorize.requestMatchers("/students/login**").permitAll()
                                    .requestMatchers("/students/home").permitAll()
                                    .requestMatchers("/students").hasRole("ADMIN")
                                    .requestMatchers("/students/{studentId}/view").hasAnyRole("ADMIN", "USER")
                                    .requestMatchers("/students/{studentId}/edit").hasRole("ADMIN")
                                    .requestMatchers("/students/userRegistration**").permitAll()
                                    .requestMatchers("/students/registrationSuccess").permitAll()
                                    .requestMatchers("/students/saveUser").permitAll()
                                    .requestMatchers("/students/searchUser**").hasAnyRole("ADMIN", "USER")
                                    .requestMatchers("/students/searchUser/**").hasAnyRole("ADMIN", "USER");
                        })
                .formLogin(
                        form -> form.loginPage("/students/login")
                                .loginProcessingUrl("/students/login")
                                .defaultSuccessUrl("/students/home")

                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );


        return httpSecurity.build();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}

