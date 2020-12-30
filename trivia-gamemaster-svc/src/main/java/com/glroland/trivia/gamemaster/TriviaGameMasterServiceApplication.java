package com.glroland.trivia.gamemaster;

import com.fasterxml.classmate.TypeResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TriviaGameMasterServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(TriviaGameMasterServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TriviaGameMasterServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
		  .build();   
		  
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				// .paths(PathSelectors.any()).build().pathMapping("/")
				// .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				// .alternateTypeRules(newRule(
				// 		typeResolver.resolve(DeferredResult.class,
				// 				typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
				// 		typeResolver.resolve(WildcardType.class)))
				// .useDefaultResponseMessages(false)
				// .globalResponses(HttpMethod.GET, singletonList(new ResponseBuilder().code("500")
				// 		.description("500 message").representation(MediaType.TEXT_XML)
				// 		.apply(r -> r.model(m -> m.referenceModel(ref -> ref
				// 				.key(k -> k.qualifiedModelName(q -> q.namespace("some:namespace").name("ERROR"))))))
				// 		.build()))
				// .securitySchemes(singletonList(apiKey())).securityContexts(singletonList(securityContext()))
				// .enableUrlTemplating(true)
				// .globalRequestParameters(singletonList(
				// 		new springfox.documentation.builders.RequestParameterBuilder().name("someGlobalParameter")
				// 				.description("Description of someGlobalParameter").in(ParameterType.QUERY)
				// 				.required(true).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).build()))
				// .tags(new Tag("Pet Service", "All apis relating to pets"))
				// .additionalModels(typeResolver.resolve(AdditionalModel.class));
	}

	@Autowired
	private TypeResolver typeResolver;

//	private ApiKey apiKey() {
//		return new ApiKey("mykey", "api_key", "header");
//	}

//	private SecurityContext securityContext() {
//		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/anyPath.*"))
//				.build();
//	}

//	List<SecurityReference> defaultAuth() {
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//		return singletonList(new SecurityReference("mykey", authorizationScopes));
//	}

//	@Bean
//	SecurityConfiguration security() {
//		return SecurityConfigurationBuilder.builder().clientId("test-app-client-id")
//				.clientSecret("test-app-client-secret").realm("test-app-realm").appName("test-app").scopeSeparator(",")
//				.additionalQueryStringParams(null).useBasicAuthenticationWithAccessCodeGrant(false)
//				.enableCsrfSupport(false).build();
//	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE).displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null)
				.operationsSorter(OperationsSorter.ALPHA).showExtensions(false).showCommonExtensions(false)
				.tagsSorter(TagsSorter.ALPHA).supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null).build();
	}

	// @Bean
	// MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
	// return new MongoTransactionManager(dbFactory);
	// }
}
