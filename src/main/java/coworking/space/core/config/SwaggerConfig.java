package coworking.space.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger para la API de Coworking Space.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura y devuelve una instancia de OpenAPI personalizada.
     *
     * @return una instancia de OpenAPI con la información de la API.
     */
    @Bean
    public OpenAPI customOpenAPI( ) {
        return new OpenAPI( ).info( new Info( ).title( "Coworking Space API" ).version( "1.0" ).description( "API para gestionar las reservas de coworking space" ) );
    }
}