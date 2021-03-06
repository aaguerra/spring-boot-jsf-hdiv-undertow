/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prueba;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Angel
 */

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    
    
  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * DataSource definition for database connection. Settings are read from
   * the application.properties file (using the env object).
   */
//    @Bean
//  public DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName(env.getProperty("db.driver"));
//    dataSource.setUrl(env.getProperty("db.url"));
//    dataSource.setUsername(env.getProperty("db.username"));
//    dataSource.setPassword(env.getProperty("db.password"));
//    return dataSource;
//  }
  
  @Bean
  public DataSource dataSource() {
    HikariConfig config1 = new HikariConfig();
    
    config1.setJdbcUrl("jdbc:postgresql://localhost:5432/sistema_contable_erp");
    config1.setUsername("postgres");
    config1.setPassword("123456");
            //config.addDataSourceProperty("cachePrepStmts", "true");
    final HikariDataSource ds = new HikariDataSource(config1);
    ds.setMaximumPoolSize(100);
    ds.setDataSourceClassName("com.zaxxer.hikari.HikariDataSource");    
    ds.addDataSourceProperty("minimumIdle", 5);
    ds.addDataSourceProperty("maximumPoolSize", 30);
    
    
//    ds.addDataSourceProperty("cachePrepStmts", true);
//    ds.addDataSourceProperty("prepStmtCacheSize", 250);
//    ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
//    ds.addDataSourceProperty("useServerPrepStmts", true);
    return ds;
  }

  /**
   * Declare the JPA entity manager factory.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactory =
        new LocalContainerEntityManagerFactoryBean();
    
    entityManagerFactory.setDataSource(dataSource);
    
    // Classpath scanning of @Component, @Service, etc annotated class
    entityManagerFactory.setPackagesToScan("erp.entity");
    
    // Vendor adapter
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
    
    // Hibernate properties
    Properties additionalProperties = new Properties();
    additionalProperties.put(
        "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    additionalProperties.put(
        "hibernate.show_sql", true);
    additionalProperties.put(
        "hibernate.hbm2ddl.auto", "update");
    entityManagerFactory.setJpaProperties(additionalProperties);
    //entityManagerFactory
    return entityManagerFactory;
  }

  /**
   * Declare the transaction manager.
   */
  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = 
        new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        entityManagerFactory.getObject());
    return transactionManager;
  }
  
  /**
   * PersistenceExceptionTranslationPostProcessor is a bean post processor
   * which adds an advisor to any bean annotated with Repository so that any
   * platform-specific exceptions are caught and then rethrown as one
   * Spring's unchecked data access exceptions (i.e. a subclass of 
   * DataAccessException).
   */
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }


  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @Autowired
  private DataSource dataSource;

  @Autowired
  private LocalContainerEntityManagerFactoryBean entityManagerFactory;


    
}
