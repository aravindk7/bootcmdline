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
 * @author Aravind
 *
 */
@Configuration
public class AppConfig {

	// TCP port for remote connections, default 9092
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
	// @ConditionalOnExpression("${h2.web.enabled:true}")
	public Server h2WebServer() throws SQLException {
		return Server.createWebServer("-web", "-webAllowOthers", "-webPort", h2webPort).start();
	}
}
