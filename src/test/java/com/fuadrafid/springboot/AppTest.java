package com.fuadrafid.springboot;

import com.fuadrafid.springboot.controller.EmployeeController;
import com.fuadrafid.springboot.controller.MathController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AppTest {

    @Autowired
    MathController mathController;

    @Autowired
    EmployeeController employeeController;

    @Test
    void test_contextLoads() {
        assertThat(mathController).isNotNull();
        assertThat(employeeController).isNotNull();
    }

}
