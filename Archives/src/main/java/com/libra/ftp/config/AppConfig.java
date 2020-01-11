package com.libra.ftp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.libra.ftp.Constants.BackendConstants;

@Configuration
@EnableWebMvc
@ComponentScan("com.libra.ftp")
public class AppConfig implements WebMvcConfigurer{

	
	
	@Bean
	public ViewResolver resolver()
	{
		InternalResourceViewResolver view=new InternalResourceViewResolver();
		view.setPrefix(BackendConstants.DISPATCHERPREFIX);
		view.setSuffix(BackendConstants.DISPATCHERSUFFIX);
		return view;
	}
}
