package com.fuadrafid.springboot.config;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerConfigTest {

    SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    void test_buildSwagger_checkDocketIsNotNull() {
        Docket docket = swaggerConfig.buildSwagger();
        assertThat(docket).isNotNull();
    }
}