package com.boolstore.bookstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
//@ComponentScan(basePackages = "com.boolstore.bookstoreapi")
public class BookstoreapiApplication {

	public static void main(String[] args) {


		SpringApplication.run(BookstoreapiApplication.class, args);

	}

}
