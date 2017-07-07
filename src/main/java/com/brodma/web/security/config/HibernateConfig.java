package com.brodma.web.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix="hibernate")
public class HibernateConfig {

    private Boolean show_sql;
    private Boolean format_sql;
    private Boolean generateStatistics;
    private Boolean generateDdl;

    public Boolean getShow_sql() {
        return show_sql;
    }

    public void setShow_sql(Boolean show_sql) {
        this.show_sql = show_sql;
    }

    public Boolean getFormat_sql() {
        return format_sql;
    }

    public void setFormat_sql(Boolean format_sql) {
        this.format_sql = format_sql;
    }

    public Boolean getGenerateStatistics() {
        return generateStatistics;
    }

    public void setGenerateStatistics(Boolean generateStatistics) {
        this.generateStatistics = generateStatistics;
    }

    public Boolean getGenerateDdl() {
        return generateDdl;
    }

    public void setGenerateDdl(Boolean generateDdl) {
        this.generateDdl = generateDdl;
    }

    @Bean(name="HibernateProperties")
    public Map<String, Object> hibernateProperties() {
        Map<String,Object> props = new HashMap<>();
        props.put("hibernate.format_sql", format_sql);
        props.put("hibernate.show_sql", format_sql);
        return props;
    }
}
