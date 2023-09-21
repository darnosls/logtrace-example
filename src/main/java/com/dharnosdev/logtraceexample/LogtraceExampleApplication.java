package com.dharnosdev.logtraceexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Hooks;


@SpringBootApplication
public class LogtraceExampleApplication {

	public static void main(String[] args) {
		Hooks.enableAutomaticContextPropagation();
		SpringApplication.run(LogtraceExampleApplication.class, args);
	}


}
