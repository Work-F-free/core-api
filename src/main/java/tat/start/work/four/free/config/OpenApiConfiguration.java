package tat.start.work.four.free.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import static org.springdoc.core.utils.Constants.API_DOCS_URL;
import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL_YAML;
import static org.springdoc.core.utils.Constants.SPRINGDOC_ENABLED;
import static org.springdoc.core.utils.Constants.SWAGGER_CONFIG_URL;
import static org.springdoc.core.utils.Constants.SWAGGER_INITIALIZER_URL;
import static org.springdoc.core.utils.Constants.SWAGGER_UI_PATH;
import static org.springdoc.core.utils.Constants.SWAGGER_UI_URL;

@Configuration
@ConditionalOnClass(OpenAPI.class)
@ConditionalOnProperty(SPRINGDOC_ENABLED)
public class OpenApiConfiguration {

    @Bean
    OpenAPI openAPI(
            @Value("${application.name}") String applicationName,
            @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuerUri
    ) {
        Scopes scopes = new Scopes().addString("profile", "Read your profile");
        String securitySchemaName = "OAuth2";
        String authorizationUrl = issuerUri + "/protocol/openid-connect/auth";
        String tokenUrl = issuerUri + "/protocol/openid-connect/token";
        return new OpenAPI()
                .info(new Info().title(applicationName))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemaName))
                .components(
                        new Components().addSecuritySchemes(
                                securitySchemaName,
                                new SecurityScheme().type(SecurityScheme.Type.OAUTH2).flows(
                                        new OAuthFlows().authorizationCode(
                                                        new OAuthFlow().authorizationUrl(authorizationUrl).tokenUrl(tokenUrl).scopes(scopes)
                                                )
                                                .clientCredentials(
                                                        new OAuthFlow().tokenUrl(tokenUrl).refreshUrl(tokenUrl).scopes(scopes))
                                )
                        )
                );
    }

    @Bean
    WebSecurityCustomizer openApiWebSecurityCustomizer(
            @Value(API_DOCS_URL) String apiDocsUrl,
            @Value(DEFAULT_API_DOCS_URL_YAML) String apiDocsYaml,
            @Value(SWAGGER_CONFIG_URL) String swaggerConfigUrl,
            @Value(SWAGGER_UI_URL) String swaggerUiUrl,
            @Value(SWAGGER_UI_PATH) String swaggerUiPath,
            @Value(SWAGGER_INITIALIZER_URL) String initializerUrl
    ) {
        return web -> web.ignoring().requestMatchers(HttpMethod.GET, apiDocsUrl, apiDocsYaml, swaggerConfigUrl,
                swaggerUiPath, swaggerUiUrl, initializerUrl, "/swagger-ui/**");
    }

}
