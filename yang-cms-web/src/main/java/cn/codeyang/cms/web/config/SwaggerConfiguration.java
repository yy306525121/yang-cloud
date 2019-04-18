package cn.codeyang.cms.web.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author yangzhongyang
 */
@Configuration
@EnableSwagger2
@Profile("swagger")
public class SwaggerConfiguration {
	@Value("${spring.application.name}")
	private String serviceName;


	private String serviceDesc = "CMS";


	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	private String OAuthServerUri = "http://localhost:8888";

	private static final Predicate<String> SWAGGER_PATHS = PathSelectors.regex("^(?!.*error$).*");
	public static final String securitySchemaOAuth2 = "oauth2schema";

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("v2")
				.apiInfo(apiInfo()).select().paths(postPaths())
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(springBootActuatorJmxPaths())
				.build()
				.securitySchemes(Collections.singletonList(securitySchema()))
				.securityContexts(Collections.singletonList(securityContext()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(serviceName).description(serviceDesc).build();
	}


	private Predicate<String> postPaths() {
		return regex("/.*");
	}

	private Predicate<String> springBootActuatorJmxPaths() {
		return regex("^/(?!env|restart|pause|resume|refresh).*$");
	}


	private OAuth securitySchema() {
		final AuthorizationScope authorizationScope =
				new AuthorizationScope("openid", "允许测试阶段访问的所有接口");
		//final LoginEndpoint loginEndpoint = new LoginEndpoint(OAuthServerUri + "/oauth/authorize");
		//final GrantType grantType = new ImplicitGrant(loginEndpoint, "access_token");
		final GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(OAuthServerUri + "/oauth/token");
		return new OAuth(securitySchemaOAuth2, Arrays.asList(authorizationScope), Arrays.asList(grantType));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(this.defaultAuth())
				.forPaths(SWAGGER_PATHS)
				.build();
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration("web_app", "hello", "", "", "", ApiKeyVehicle.HEADER, "",
				" ");
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope authorizationScope =
				new AuthorizationScope("openid", "允许测试阶段访问的所有接口");
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(
				new SecurityReference(securitySchemaOAuth2, authorizationScopes));
	}
}
