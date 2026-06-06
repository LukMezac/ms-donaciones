package com.donaton.donacion;

import com.donaton.donaciones.MsDonacionesApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;


@AutoConfigureMockMvc // Si usas MockMvc
// Excluye la configuración automática de la BD para los tests unitarios
@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration")
class MsDonacionesApplicationTests {
	// ...
}