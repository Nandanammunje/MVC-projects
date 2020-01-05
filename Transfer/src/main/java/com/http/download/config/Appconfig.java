package com.http.download.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.http.download")
@EnableWebMvc
public class Appconfig  implements WebMvcConfigurer  {

	
	
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver view =new InternalResourceViewResolver();
		view.setPrefix(BackendConstants.DISPATCHERPATHPREFIX);
		view.setSuffix(BackendConstants.DISPATCHERPATHSUFFIX);
		return view; 
	}
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
