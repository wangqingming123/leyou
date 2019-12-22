package com.leyou.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {


    @Bean
    public CorsFilter corsFilter(){

        CorsConfiguration configurationSource=new CorsConfiguration();

        //允许的域,不要写*，否则cookie就无法使用了
        configurationSource.addAllowedOrigin("http://manage.leyou.com");
        //是否发送cookie信息
        configurationSource.setAllowCredentials(true);
        //允许的请求方式
        configurationSource.addAllowedMethod("*");
        //允许的头信息
        configurationSource.addAllowedHeader("*");
        //此次预检时长，有效时长内无需预检直接跨域访问、
        configurationSource.setMaxAge(3600L);

        //添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", configurationSource);
        return new CorsFilter(configSource);
    }
}
