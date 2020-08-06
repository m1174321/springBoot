package com.fh.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {


    @Bean
    public Docket apiDocket() {
        //select（）创建一个构建器，用于定义哪些控制器及其生成的文档中应包含哪些方法。
        //apis（）定义要包含的类（控制器和模型类）。这里我们包括所有这些，但您可以通过基础包，类注释等来限制它们。
        //paths（）允许您根据路径映射定义应包含哪个控制器的方法。我们现在包括所有这些，但您可以使用正则表达式等限制它。
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fh.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
