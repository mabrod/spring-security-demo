package com.brodma.web.security.config;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HikariCPProperty.class)
public class HikariCPConfig {

    @Autowired
    private HikariCPProperty hikariCPProperty;

    @Bean(name="hikariConfig")
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMinimumIdle(hikariCPProperty.getMinimumIdle());
        hikariConfig.setMaximumPoolSize(hikariCPProperty.getMaximumPoolSize());
        hikariConfig.setConnectionTimeout(hikariCPProperty.getConnectionTimeout());
        hikariConfig.setConnectionTestQuery(hikariCPProperty.getConnectionTestQuery());
        hikariConfig.setPoolName(hikariCPProperty.getPoolName());
        hikariConfig.setLeakDetectionThreshold(hikariCPProperty.getLeakDetectionThreshold());
        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", hikariCPProperty.isCachePrepStmts());
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", hikariCPProperty.getPrepStmtCacheSize());
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", hikariCPProperty.getPrepStmtCacheSqlLimit());
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", hikariCPProperty.isUseServerPrepStmts());
        return hikariConfig;
    }
}
