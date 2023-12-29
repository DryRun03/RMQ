package io.pankaj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmqApplication.class, args);
		System.out.println("Hello World");
	}

}
