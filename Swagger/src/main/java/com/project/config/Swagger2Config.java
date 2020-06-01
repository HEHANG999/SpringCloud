package com.project.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import io.swagger.annotations.Api;


/**
 * swagger2配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                /**为当前包下的controller生成接口*/
                .apis(RequestHandlerSelectors.basePackage("com.project.controller"))
                /**为有@Api注解的controller生成接口*/
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                /**为有@ApiOperation注解的方法生成API文档*/
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                /**为任何接口生成API文档*/
                //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("使用Swagger2构建Api文档")
                //创建人
                .contact(new Contact("张三","www.com","3139210087@qq.com"))
                //版本号
                .version("版本1.0")
                //描述
                .description("测试描述：没有什么可写。。")
                .build();
    }

}
