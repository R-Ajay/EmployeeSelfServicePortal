//package com.cognizant.employeeselfservice.configuration;
//
//import com.cognizant.employeeselfservice.filter.AuthoritiesLoggingAtFilter;
//import com.cognizant.employeeselfservice.filter.CsrfCookieFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.Collections;
//
//@Configuration
//public class SecurityConfig {
//
//    CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//
//
//        httpSecurity.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
//            @Override
//            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                CorsConfiguration config = new CorsConfiguration();
//                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//                config.setAllowedMethods(Collections.singletonList("*"));
//                config.setAllowCredentials(true);
//                config.setAllowedHeaders(Collections.singletonList("*"));
//                config.setMaxAge(3600L);
//                return config;
//            }
//        }));
//
//        httpSecurity.csrf(httpSecurityCsrfConfigurer ->
//                         httpSecurityCsrfConfigurer.csrfTokenRequestHandler(requestHandler)
//                                 .ignoringRequestMatchers("Any Public API with post operation")
//                                 .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
//                 .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                         authorizationManagerRequestMatcherRegistry
//                                 .requestMatchers("Api").hasAuthority("Defind the authorities based on that it will allow access to that corresponding url")
//                                 .anyRequest()
//                                  .permitAll())
//                .httpBasic(Customizer.withDefaults());
//
//        return httpSecurity.build();
//
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncouder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//
//}
