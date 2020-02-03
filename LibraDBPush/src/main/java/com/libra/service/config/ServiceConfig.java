package com.libra.service.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.libra.service")
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql.properties" })
public class ServiceConfig  implements WebMvcConfigurer{

	@Autowired
	public Environment env;
	
	@Bean
	public DataSource RestDataSource()
	{
		ComboPooledDataSource datasource=new ComboPooledDataSource();
		try {
			datasource.setDriverClass("com.mysql.jdbc.Driver");
			datasource.setJdbcUrl(env.getProperty("jdbc.url"));
			datasource.setUser(env.getProperty("jdbc.user"));
			datasource.setPassword(env.getProperty("jdbc.password"));
			
			// set connection pool props
			datasource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
			datasource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
			datasource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
			datasource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

			
			
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasource;
		
	}
	private Properties getHibernateProperties() {

		// set hibernate properties
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;				
	}
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factorys
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(RestDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}	
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}	
	
}
