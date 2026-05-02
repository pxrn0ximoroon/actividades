package com.taller.actividades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Clase principal de la aplicación Spring Boot.
 * Aquí arranca todo el sistema y se configura CORS.
 *
 * @author Julian Andres - Andres Espinosa - Miguel David
 * @version 1.0
 * @since 2026
 */
@SpringBootApplication
public class ActividadesApplication {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(ActividadesApplication.class, args);
    }

    /**
     * Configura CORS para permitir que el frontend se conecte al backend.
     * Acepta peticiones de cualquier origen con GET, POST, PUT y DELETE.
     *
     * @return Configuración CORS lista para usar
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
