package com.thrcat.conf;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/06
 */
@Configuration
public class SpringBeans {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setProcessPropertyPlaceHolders(false);
        configurer.setBasePackage("com.thrcat.mapper");
        return configurer;
    }

}
