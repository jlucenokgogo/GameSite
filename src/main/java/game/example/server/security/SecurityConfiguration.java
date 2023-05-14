package game.example.server.security;


import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@Configuration("SecurityConfiguration")
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
                //.requestMatchers().permitAll()
               // .requestMatchers().permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cc = new CorsConfiguration();
        cc.setAllowedHeaders(Collections.singletonList("*"));
        cc.setExposedHeaders(Collections.singletonList("*"));
        cc.setAllowedOriginPatterns(Collections.singletonList("*"));
        cc.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE"));
        cc.addAllowedOrigin("*");
        cc.setMaxAge(Duration.ZERO);
        cc.setAllowCredentials(Boolean.FALSE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cc);
        return source;
    }



}
