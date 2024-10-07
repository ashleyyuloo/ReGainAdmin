// package com.regain.adminzn.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {

//     @Autowired
//     private AdminDetailService adminDetailService;

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//         return httpSecurity
//         .csrf(AbstractHttpConfigurer::disable)
//         .authorizeHttpRequests(registry->{
//             registry.requestMatchers("/login/**").permitAll();
//             registry.requestMatchers("/register/**").hasRole("admin");
//             registry.requestMatchers("/reports/**").hasRole("admin");
//             registry.requestMatchers("/dashboard/**").hasRole("admin");
//             registry.requestMatchers("/equipment/**").hasRole("admin");
//             registry.requestMatchers("/products/**").hasRole("admin");
//             registry.requestMatchers("/transactions/**").hasRole("admin");
//             registry.requestMatchers("/users/**").hasRole("admin");
//             registry.anyRequest().authenticated();
//         })
//         .formLogin(httpSecurityFormLoginConfiigurer ->{
//             httpSecurityFormLoginConfiigurer.loginPage("/login").permitAll();
//         })
//         .build();
//     }

//     @Bean
//     public AuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//         provider.setUserDetailsService(adminDetailService);
//         provider.setPasswordEncoder(passwordEncoder());
//         return provider;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }
// }

