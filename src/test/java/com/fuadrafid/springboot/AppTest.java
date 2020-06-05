package com.fuadrafid.springboot;

import com.fuadrafid.springboot.controller.ExternalRestApiController;
import com.fuadrafid.springboot.controller.MathController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AppTest {

    @Autowired
    MathController mathController;

    @Autowired
    ExternalRestApiController externalRestApiController;

    @Test
    public void contextLoads() {
        assertThat(mathController).isNotNull();
        assertThat(externalRestApiController).isNotNull();
    }

}
