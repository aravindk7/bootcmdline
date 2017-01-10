package com.lonsec.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lonsec.service.parser.CSVFileParserFactory;

/**
 * Bean Configurations
 * @author Aravind
 *
 */
@Configuration
public class AppConfig {

	@Value("${h2.webPort:8082}")
	private String h2webPort;

	@SuppressWarnings("rawtypes")
	@Bean
	public FactoryBean serviceLocatorFactoryBean() {
		ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
		factoryBean.setServiceLocatorInterface(CSVFileParserFactory.class);
		return factoryBean;
	}

	@Bean
	public Server h2WebServer() throws SQLException {
		return Server.createWebServer("-web", "-webAllowOthers", "-webPort", h2webPort).start();
	}
}
