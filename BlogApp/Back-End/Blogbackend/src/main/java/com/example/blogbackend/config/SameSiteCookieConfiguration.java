//package com.example.blogbackend.config;
//
//import org.apache.catalina.Context;
//import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
//import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SameSiteCookieConfiguration {
//
//    @Bean
//    public TomcatContextCustomizer tomcatContextCustomizer() {
//        return (context) -> {
//            Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
//            cookieProcessor.setSameSiteCookies("None");
//            context.setCookieProcessor(cookieProcessor);
//        };
//    }
//}
