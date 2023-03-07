package com.jsp.jspwfm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JspWfmApplication {
	static Logger log=LogManager.getLogger("JspWfmApplication.class");

	public static void main(String[] args) {
		SpringApplication.run(JspWfmApplication.class, args);
		log.info("this is info message");
		log.warn("this is info message");
	}

}
