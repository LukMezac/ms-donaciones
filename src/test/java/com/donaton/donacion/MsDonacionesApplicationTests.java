package com.donaton.donacion;

import com.donaton.donaciones.MsDonacionesApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

@SpringBootTest(classes = MsDonacionesApplication.class) // Importa la clase principal aquí
class MsDonacionesApplicationTests {
	@Test
	void contextLoads() { }
}