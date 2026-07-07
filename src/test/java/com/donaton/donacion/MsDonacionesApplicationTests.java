package com.donaton.donacion;

import com.donaton.donaciones.MsDonacionesApplication; // Importamos tu clase principal real
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(
        classes = MsDonacionesApplication.class,
        properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
)
class MsDonacionesApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que el contexto cargue
    }
}