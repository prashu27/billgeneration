package com.xebia.assessment.BillGeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author prashansa.shukla
 *BillGeneration  application flow starts from  here. 
 *Application will provide the discounted amount based on the user type
 *e.g if User is Employee of the store, discounted amount will be 30%
 *
 */
@SpringBootApplication(scanBasePackages={"com.xebia"})
@EnableWebMvc
public class BillGenerationApplication {


	/** Main method from  where the execution will get  started
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BillGenerationApplication.class, args);
	}

}
