package com.fluidnotions.springdocngxformly;

import org.springdoc.core.OpenAPIService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({OpenAPIService.class})
public class SpringDocNgxFormlyAutoConfiguration {

}
