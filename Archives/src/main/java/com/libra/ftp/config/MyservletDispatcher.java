package com.libra.ftp.config;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyservletDispatcher  extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {AppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
      @Override
	protected void customizeRegistration(Dynamic registration)
	{
		MultipartConfigElement multipartelement=new MultipartConfigElement("C:/Gate-ECE/",100000000,200000000,0);
		registration.setMultipartConfig(multipartelement);
	}
	
}
