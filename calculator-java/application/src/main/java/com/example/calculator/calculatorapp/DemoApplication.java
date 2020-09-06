package com.example.calculator.calculatorapp;

import com.example.calculator.service.Calculator;
import com.example.calculator.service.MyService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.example.calculator")
@RestController
public class DemoApplication {

	private final MyService myService;

	public DemoApplication(MyService myService) {
		this.myService = myService;
	}

	@GetMapping("/")
	public String home() {
		return myService.message();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/calc")
	public String calc(@RequestParam String text) {
		if (text.equals(""))
			return "";

		try {
			return Calculator.calc(text).toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
