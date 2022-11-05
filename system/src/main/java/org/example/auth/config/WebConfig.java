package org.example.auth.config;

import org.example.auth.interceptor.JwtInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bswaterb
 * @date 2022-11-05 11:35:36
 */
@Component
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath  =new ArrayList<>();
        //除了以下的接口都需要带上token进行访问
        excludePath.add("/");
        excludePath.add("/api/v1/login");
        excludePath.add("/csrf/**");
        excludePath.add("/system/test");
        excludePath.add("/error/**");
        excludePath.add("/register/**");
        excludePath.add("/swagger-ui.html");
        excludePath.add("/**swagger**/**");
        excludePath.add("/webjars/**");
        excludePath.add("/resources/**");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/v2/**");

        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24);
    }
}
