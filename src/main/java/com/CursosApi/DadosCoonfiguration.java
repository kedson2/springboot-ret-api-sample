package com.CursosApi;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DadosCoonfiguration {
	@Bean
	public DataSource datasource() {
		DriverManagerDataSource datasouce = new DriverManagerDataSource();
		datasouce.setDriverClassName("com.mysql.jdbc.Driver");
		datasouce.setUrl("jdbc:mysql://localhost:3306/eventosapp");// nome de bamcoe a porta
		datasouce.setUsername("root");
		datasouce.setPassword("kdanjoskd");
		return datasouce;

	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernate = new HibernateJpaVendorAdapter();
		Properties jpaProperties = new Properties();
		hibernate.setDatabase(Database.MYSQL);
		hibernate.setShowSql(true);
		hibernate.setGenerateDdl(true);
		jpaProperties.put("hibernate.format_sql", "true");
		hibernate.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
		hibernate.setPrepareConnection(true);
		return hibernate;

	}

}
