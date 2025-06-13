package com.tccfer.application.security;

import com.tccfer.application.login.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Permitir acesso a imagens (se quiser manter)
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(new AntPathRequestMatcher("/images/*.png")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/line-awesome/**/*.svg")).permitAll()
            // Liberar acesso ao H2 console
            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
        );

        // Desabilitar CSRF e frame options para o H2 console funcionar corretamente
        http.csrf(csrf -> csrf
            .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
        );
        http.headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions.disable())
        );

        super.configure(http);
        setLoginView(http, LoginView.class);
    }

}
