package com.RFERP.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.RFERP.constants.IERPConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author diego
 *
 */
@Configuration
@ComponentScan(basePackages = { IERPConstants.PACKAGES_SCAN_COMPONENTS })
public class RFERPDbConfig {
	@Primary
	@Bean(name = "dataSourceERP")
	public DataSource dataSourceERP() {
		return new HikariDataSource(this.dataSourceDev());
	}

	private HikariConfig dataSourceProd() {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
		config.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/erp?serverTimezone=UTC");
		config.addDataSourceProperty("user", "root");
		config.addDataSourceProperty("password", "root");
		config.setIdleTimeout(300000);
		config.setAutoCommit(true);
		config.setConnectionTimeout(20000);

		return config;
	}

	private HikariConfig dataSourceDev() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.p6spy.engine.spy.P6SpyDriver");
		config.setJdbcUrl("jdbc:p6spy:mysql://localhost:3306/erp?serverTimezone=UTC");
		config.addDataSourceProperty("user", "root");
		config.addDataSourceProperty("password", "root");
		config.setIdleTimeout(300000);
		config.setAutoCommit(true);
		config.setConnectionTimeout(20000);

		return config;
	}

	@Primary
	@Bean(name = "erpManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com");
		factory.setDataSource(dataSourceERP());
		return factory;
	}

	@Primary
	@Bean(name = "erpTransactionManager")
	public PlatformTransactionManager transactionManagerERP(
			@Qualifier("erpManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
		return new JpaTransactionManager(customerEntityManagerFactory);
	}

	@Bean(name = "erpTransactionInterceptor")
	public TransactionInterceptor transactionInterceptor(
			@Qualifier("erpTransactionManager") PlatformTransactionManager platformTransactionManager) {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		transactionInterceptor.setTransactionManager(platformTransactionManager);
		Properties transactionAttributes = new Properties();
		transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED,-Throwable");
		transactionAttributes.setProperty("tranNew*", "PROPAGATION_REQUIRES_NEW,-Throwable");
		transactionInterceptor.setTransactionAttributes(transactionAttributes);
		return transactionInterceptor;
	}

	@Bean
	public BeanNameAutoProxyCreator transactionAutoProxy() {
		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
		transactionAutoProxy.setProxyTargetClass(true);
		transactionAutoProxy.setBeanNames("*Service");
		transactionAutoProxy.setInterceptorNames("erpTransactionInterceptor");
		return transactionAutoProxy;
	}
}
