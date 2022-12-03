package pers.guo.repositorytemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //声明该类为配置类
@EnableSwagger2 //声明启动Swagger2
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //此处根据情况自行添加需要将哪些接口纳入Swagger 文档管理。此处应用basePackage管理，还可以利用注解管理
                //如果填写错误的话会出现“No operations defined in spec!” 的问题。
                //.apis(RequestHandlerSelectors.basePackage("com.abner.springboottemplate.controller"))//扫描的包路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot-template-Swagger2-RESTFUL API")//文档说明
                .description("描述信息")
                .termsOfServiceUrl("https://blog.csdn.net/Abner_G")
                .version("1.0.0")//文档版本说明
                .build();
    }
}