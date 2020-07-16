package com.example.securityDemo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PasswordConfiguration {
/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */
    // nefunguje mi to, bcrypt ide normalne a ale ostatne mi nesli
    // dal som natvrdo uz zahesovane heslo do databazy
    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("sha256", new StandardPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put(encodingId, new BCryptPasswordEncoder(10, new SecureRandom()));

        DelegatingPasswordEncoder passwordEncoder =
                new DelegatingPasswordEncoder(encodingId, encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(encoders.get(encodingId));

        return passwordEncoder;
    }

}
