package com.stock.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class JDBCConfig {
	@Value("${spring.datasource.name}")
	String name;

	@Value("${spring.datasource.password}")
	String password;

	@Value("${spring.datasource.url}")
	String url;

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws Exception {
		Properties properties = new Properties();

		properties.setProperty("username", name);
		properties.setProperty("password", password);
		properties.setProperty("url", url);

		DataSource createDataSource = BasicDataSourceFactory.createDataSource(properties);
		System.out.println(createDataSource);
		return new NamedParameterJdbcTemplate(createDataSource);
	}
}
