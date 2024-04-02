package com.cognizant.employeeselfservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Ajay R",
                        email = "ajay.raja@cognizant.com"
                ),
                description = "Employee Self Service app documentation",
                title = "OpenApi specification - Ajay R",
                version = "1.0",
                license = @License(
                        name = "License Name",
                        url = "http://licenseurl.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local Env",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfiguration {


}