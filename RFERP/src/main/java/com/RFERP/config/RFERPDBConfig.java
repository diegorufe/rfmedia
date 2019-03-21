package com.RFERP.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class RFERPDBConfig {

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
		config.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/erp?serverTimezone=UTC");
		config.addDataSourceProperty("user", "root");
		config.addDataSourceProperty("password", "root");
		config.setIdleTimeout(300000);
		config.setAutoCommit(true);
		config.setConnectionTimeout(20000 );
		
		return new HikariDataSource(config);
	}
}
