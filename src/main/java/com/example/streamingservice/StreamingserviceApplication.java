package com.example.streamingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication//то что в скобках выключает подключение к БД
@EnableJpaRepositories
public class StreamingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingserviceApplication.class, args);
	}

}