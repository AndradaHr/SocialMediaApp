package com.app.project.config;

import com.app.project.authentication.ApiJsonWebTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public FilterRegistrationBean<ApiJsonWebTokenFilter> apiJsonWebTokenFilterRegistrationBean() {
        FilterRegistrationBean<ApiJsonWebTokenFilter> registrationBean = new FilterRegistrationBean<>();
        ApiJsonWebTokenFilter apiJsonWebTokenFilter = new ApiJsonWebTokenFilter();
        registrationBean.setFilter(apiJsonWebTokenFilter);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addInitParameter("excludedPaths", "/api/login,/api/user/register,/api/user/{userId}/change-password,/api/user/forgot-password/*");

        return registrationBean;
    }
}
