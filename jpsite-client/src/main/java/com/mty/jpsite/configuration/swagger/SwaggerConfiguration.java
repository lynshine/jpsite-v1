package com.mty.jpsite.configuration.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger 配置类
 */
@Configuration
/**启用Swagger2Configuration*/
@EnableSwagger2
public class SwaggerConfiguration {
    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket controllerApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerProperties.getGroupName())
                .apiInfo(apiInfo());

        ApiSelectorBuilder builder = docket.select();
        /**只扫描该controller包下的controller*/
        if (!StringUtils.isEmpty(swaggerProperties.getBasePackage())) {
            builder.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()));
        }
        /**只匹配指定的request mapping URL*/
        if (!StringUtils.isEmpty(swaggerProperties.getAntPath())) {
            builder.paths(PathSelectors.ant(swaggerProperties.getAntPath()));
        }

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl("http://springfox.io")
                .contact("jpsite")
                .license(swaggerProperties.getLicense())
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

    /**
     * 默认分组
     *
     * @return Docket
     */
    @Bean
    public Docket defaultApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BasicErrorController.class.getPackage().getName()))
                .build();
        return docket;
    }
}
