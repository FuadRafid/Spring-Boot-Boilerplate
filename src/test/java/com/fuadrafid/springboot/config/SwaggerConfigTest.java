package com.fuadrafid.springboot.config;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerConfigTest {

    SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    void calculatorApi_checkDocketIsNotNull() {
        Docket docket = swaggerConfig.calculatorApi();
        assertThat(docket).isNotNull();
    }
}