package com.ultra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.ultra.bo.Swagger2;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 *
 * @author admin
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    private Swagger2 swagger2;

    @Bean
    public Docket docket() {
        // 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                // 控制暴露出去的路径下的实例
                // 如果某个接口不想暴露,可以使用以下注解
                // @ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(basePackage(swagger2.getBasePackage())).paths(PathSelectors.any()).build();
    }

    private static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    /**
     * 处理包路径配置规则,支持多路径扫描匹配以逗号隔开
     *
     * @param basePackage 扫描包路径
     * @return Function
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return new Function<Class<?>, Boolean>() {
            @Override
            public Boolean apply(Class<?> input) {
                for (String strPackage : basePackage.split(",")) {
                    boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                    if (isMatch) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    @SuppressWarnings("deprecation")
    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swagger2.getTitle()).description(swagger2.getDescription())
                .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("").version(swagger2.getVersion())
                .contact(new Contact(swagger2.getName(), swagger2.getUrl(), swagger2.getEmail())).build();
    }
}
