package com.RFERP.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.RFERP.constants.IConstantsERP;
import com.RFSecurity.config.SecurityConfig;

@Configuration
@Import(SecurityConfig.class)
public class ERPConfig {
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
	    LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();
	    localSessionFactory.setDataSource(dataSource);
	    localSessionFactory
	            .setPackagesToScan(IConstantsERP.PACKAGES_MAPPING_ENTITITES);
	    return localSessionFactory;
	}
}
