package com.lonsec.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lonsec.service.parser.CSVFileParserFactory;

/**
 * @author Aravind
 *
 */
@Configuration
public class AppConfig {
	
	@SuppressWarnings("rawtypes")
	@Bean
	public FactoryBean serviceLocatorFactoryBean() {
		ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
		factoryBean.setServiceLocatorInterface(CSVFileParserFactory.class);
		return factoryBean;
	}
}
