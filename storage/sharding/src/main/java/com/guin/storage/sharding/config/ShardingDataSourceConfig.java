package com.guin.storage.sharding.config;

import com.guin.storage.sharding.property.ShardingDataSourceProperty;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@ConfigurationProperties(prefix = "datasource")
@EnableJpaRepositories(basePackages = "com.guin", entityManagerFactoryRef = "entityManagerFactoryBean", transactionManagerRef = "platformTransactionManager")
public class ShardingDataSourceConfig {

    private static final String DELIMITER = "|";

    private ShardingDataSourceProperty property;

    @Bean
    public DataSourceRouter shardingDataSource() {
        Map<Object, Object> dataSourceMap = new LinkedHashMap<>();
        DataSourceRouter router = new DataSourceRouter();
        AtomicInteger index = new AtomicInteger(0);

        property.shards().stream()
                .forEach(shard -> {
                    ShardingDataSourceProperty.DataSourceProperty master = shard.master();
                    ShardingDataSourceProperty.DataSourceProperty slave = shard.slave();

                    DataSource masterDataSource = DataSourceBuilder.create()
                            .driverClassName(master.driverClassName())
                            .username(master.username())
                            .password(master.password())
                            .url(master.jdbcUrl())
                            .type(HikariDataSource.class)
                            .build();

                    DataSource slaveDataSource = DataSourceBuilder.create()
                            .driverClassName(slave.driverClassName())
                            .username(slave.username())
                            .password(slave.password())
                            .url(slave.jdbcUrl())
                            .type(HikariDataSource.class)
                            .build();

                    dataSourceMap.put(index.get() + DELIMITER + shard.master().name(), masterDataSource);
                    dataSourceMap.put(index.getAndIncrement() + DELIMITER + shard.slave().name(), slaveDataSource);
                });
        router.setTargetDataSources(dataSourceMap);
        router.afterPropertiesSet();

        return router;
    }

    @Bean
    public LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy(DataSourceRouter dataSourceRouter) {
        return new LazyConnectionDataSourceProxy(dataSourceRouter);
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(JpaProperties jpaProperties,
                                                                           LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(lazyConnectionDataSourceProxy);
        em.setPackagesToScan(this.getClass().getPackageName().toString());
        em.setJpaVendorAdapter(createJpaVendorAdapter(jpaProperties));

        return em;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }


    private JpaVendorAdapter createJpaVendorAdapter(
            JpaProperties jpaProperties
    ) {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(jpaProperties.isShowSql());
        adapter.setDatabase(jpaProperties.getDatabase());
        adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        return adapter;
    }

}
