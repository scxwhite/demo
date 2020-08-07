package com.thrcat.conf;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/06
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setProcessPropertyPlaceHolders(false);
        configurer.setBasePackage("com.thrcat.mapper");
        return configurer;
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new com.alibaba.druid.pool.DruidDataSource();
    }
}
