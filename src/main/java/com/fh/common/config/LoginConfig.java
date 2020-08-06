package com.fh.common.config;

import com.fh.common.interceptor.KuaYuInterceptor;
import com.fh.common.interceptor.LoginInterceptor;
import com.fh.controller.TypeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new KuaYuInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/car/**");

//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(
//                "/user/**",
//                "/type/findTypesAll",
//                "/product/**",//登录
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.css",             //css静态资源
//                "/**/*.woff",
//                "/**/*.ttf"
//        );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
