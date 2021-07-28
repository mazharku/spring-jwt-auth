/**
 * 
 */
package com.mazhar.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author mazhar
 *
 * July 27, 2021
 */
@Configuration
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String DEFAULT_PATH = "/api/.*";

	@Bean
	public Docket swaggerSpringfoxDocket() {
		Contact contact = new Contact("Spring Security", "", "mazhar.shapnil@gmail.com");

		String DESCRIPTION = "Spring Security Api Documentation";
		String VERSION = "1.0.1";
		ApiInfo apiInfo = new ApiInfo("Spring Security", DESCRIPTION, VERSION, "", contact, "", "", new ArrayList<>());

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.pathMapping("/")
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class)
				.ignoredParameterTypes(Pageable.class)
				.ignoredParameterTypes(java.sql.Date.class)
				.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDateTime.class, Date.class)
				.securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Collections.singletonList(apiKey()))
				.useDefaultResponseMessages(false);

		docket = docket.select()
				.paths(regex(DEFAULT_PATH))
				.build();

		return docket;
	}


	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Collections.singletonList(
				new SecurityReference("JWT", authorizationScopes));
	}

	/*@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().scopeSeparator(",")
				.additionalQueryStringParams(null)
				.useBasicAuthenticationWithAccessCodeGrant(false).build();
	}*/
}
