package com.nimap.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@EnableJpaRepositories(basePackages = "com.*")
public class DbConfiguration {

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/project");
		dataSource.setUsername("root");
		dataSource.setPassword("Pass@123");
		return dataSource;
	}
	
	
	@Bean(name = "entityManagerFactory")
	LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
		//1. set DataSource database details 
		factory.setDataSource(getDataSource());
		
		//2. provide package information of entity classes to entity manager
		factory.setPackagesToScan("com.nimap.entity");
		
		//3.provide Hibernate details to entity manager
		factory.setJpaProperties(hibernateProperties());
		
		//4.passing predefined hibernate adaptor object to entity manager
		HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
		factory.setJpaVendorAdapter(vendor);
		
		
		return factory ;
	}
	
	@Bean("transactionManager")
	public PlatformTransactionManager createTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());
		return transactionManager;
	}
	
	//4. setting hibernate property in the form of key value pair.
	Properties hibernateProperties() {
	    Properties property = new Properties();
	    property.setProperty("hibernate.hbm2ddl.auto", "update");
	    property.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
	    property.setProperty("hibernate.show_sql", "true");
	    return property;
	}
}
