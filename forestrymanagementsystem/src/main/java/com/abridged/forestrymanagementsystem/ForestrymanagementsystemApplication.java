package com.abridged.forestrymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.abridged.forestrymanagementsystem.exception.AlreadyPresentException;
import com.abridged.forestrymanagementsystem.exception.EmptyRecordException;
/**
 * The ForestrymanagementsystemApplication program implements an application that executes forestry
 * management system.
 * 
 * @author Suyash
 * 
 */
@SpringBootApplication
public class ForestrymanagementsystemApplication {

	/**
	 * This is the main method.
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 * @exception NotFoundExceptionException               on input error.
	 * @exception EmptyRecordException                     on input error.
	 * @exception AlreadyPresentException                  on input error.
	 * @see NotFoundException.
	 * @see EmptyRecordException.
	 * @see AlreadyPresentException.
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(ForestrymanagementsystemApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/getalladminsrecord").allowedOrigins("http://localhost:8080");
				registry.addMapping("/addland").allowedOrigins("http://localhost:8080");
			}
		};
	}

}
