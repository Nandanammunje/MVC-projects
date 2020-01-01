package com.http.download.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.http")
@EnableWebMvc
public class Appconfig {

	
	
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver view =new InternalResourceViewResolver();
		view.setPrefix(BackendConstants.DISPATCHERPATHPREFIX);
		view.setSuffix(BackendConstants.DISPATCHERPATHSUFFIX);
		return view; 
	}
}
