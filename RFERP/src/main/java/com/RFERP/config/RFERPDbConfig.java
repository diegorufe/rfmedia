package com.RFERP.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.RFCore.constants.EnumRFProfiles;
import com.RFCore.constants.ICoreConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author diego
 *
 */
@Configuration
@ComponentScan(basePackages = { ICoreConstants.PACKAGES_SCAN_COMPONENTS })
public class RFERPDbConfig {

	@Value("${rferp.profile}")
	private String profile;

	/**
	 * Variables for database conexión dev
	 */
	@Value("${rferp.db.urlconectionDev}")
	private String urlconectionDatabaseDev;
	@Value("${rferp.db.userDev}")
	private String userDatabaseDev;
	@Value("${rferp.db.passwordDev}")
	private String passwordDatabaseDev;

	/**
	 * Variables for database conexión prod
	 */
	@Value("${rferp.db.urlconectionProd}")
	private String urlconectionDatabaseProd;
	@Value("${rferp.db.userProd}")
	private String userDatabaseProd;
	@Value("${rferp.db.passwordProd}")
	private String passwordDatabaseProd;

	@Primary
	@Bean(name = "dataSourceERP")
	public DataSource dataSourceERP() {
		return new HikariDataSource(
				EnumRFProfiles.convert(this.profile).equals(EnumRFProfiles.DEV) ? this.dataSourceDev()
						: this.dataSourceProd());
	}

	private HikariConfig dataSourceProd() {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
		// config.addDataSourceProperty("url",
		// "jdbc:mysql://localhost:3306/erp?serverTimezone=UTC");
		config.addDataSourceProperty("url", this.urlconectionDatabaseProd);
		config.addDataSourceProperty("user", this.userDatabaseProd);
		config.addDataSourceProperty("password", this.passwordDatabaseProd);
		config.setIdleTimeout(300000);
		config.setAutoCommit(true);
		config.setConnectionTimeout(20000);

		return config;
	}

	private HikariConfig dataSourceDev() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.p6spy.engine.spy.P6SpyDriver");
		config.setJdbcUrl(this.urlconectionDatabaseDev);
		config.addDataSourceProperty("user", this.userDatabaseDev);
		config.addDataSourceProperty("password", this.passwordDatabaseDev);
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
