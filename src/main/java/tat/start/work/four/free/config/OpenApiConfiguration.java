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

import static org.springdoc.core.utils.Constants.SPRINGDOC_ENABLED;

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

}
