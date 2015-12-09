/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author bad
 */
@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(Environment.BASE_PACKAGE);
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties());
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Environment.DB_URL);
        config.setUsername(Environment.DB_USERNAME);
        config.setPassword(Environment.DB_PASSWORD);
        config.setDriverClassName(Environment.DB_DRIVER_CLASS_NAME);
        config.setConnectionTestQuery("show tables");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "1000");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "4096");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("allowMultiQueries", "true");
        config.addDataSourceProperty("metadataCacheSize", "1000");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");
        config.addDataSourceProperty("cacheCallableStmts", "true");
        config.addDataSourceProperty("callableStmtCacheSize", "1000");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("useUnicode", "true");
        config.addDataSourceProperty("characterEncoding", "UTF-8");
        config.addDataSourceProperty("characterSetResults", "UTF-8");

        return new HikariDataSource(config);
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("javax.persistence.schema-generation.database.action", "update");
        properties.put("eclipselink.deploy-on-startup", "true");
        properties.put("eclipselink.ddl-generation", "create-or-extend-tables");
        properties.put("eclipselink.ddl-generation.output-mode", "database");
        properties.put("eclipselink.weaving", "static");
        properties.put("eclipselink.weaving.lazy", "true");
        properties.put("eclipselink.weaving.internal", "true");
        properties.put("eclipselink.logging.level", "SEVERE");
        properties.put("eclipselink.query-results-cache.type", "FULL");
        properties.put("eclipselink.jdbc.batch-writing", "JDBC");
        properties.put("eclipselink.jdbc.batch-writing.size", "1000");
        return properties;
    }
}
