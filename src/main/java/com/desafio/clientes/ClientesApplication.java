package com.desafio.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class ClientesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}
	@RequestMapping(value="/")
	public String index() {
		return "redirect:/clientes";
	}
	
}
