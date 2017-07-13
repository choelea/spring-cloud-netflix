package com.joe.bookmark.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Sets.*;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Autowired
	private TypeResolver typeResolver;
	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("bookmark-api").apiInfo(apiInfo())
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.joe.bookmark.controller"))
					.paths(PathSelectors.any()).build()
				/*  Using List<T> in controller instead of Collection<T>; OOTB swagger-ui will reflect the case
				 * .alternateTypeRules(
			            newRule(typeResolver.resolve(List.class, WildcardType.class),
			                typeResolver.resolve(WildcardType.class)))*/
				.useDefaultResponseMessages(false)
				.produces(newHashSet("application/json"))
				.consumes(newHashSet("application/json"))
				.ignoredParameterTypes(ApiIgnore.class).enableUrlTemplating(true);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Bookmark Service API").description("Bookmark CRUD operations")
				// .termsOfServiceUrl("http://springfox.io")
				// .contact("springfox")
				// .license("Apache License Version 2.0")
				// .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
				.version("1.0").build();
	}	
}