package io.spring.rabbitMQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude={org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration.class})
public class RabbitMqApplication {

	public static void main(String[] args) {

		System.out.println("Hello");
		SpringApplication.run(RabbitMqApplication.class, args);
	}

}
