package com.rensm.audit.service.mybatis.config;


import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Properties;

@Configuration
@AutoConfigureAfter({MybatisAutoConfiguration.class})
public class MybatisMapperScannerConfiguration {
    private final String mybatisMapperScanBasePackage = "spring.mybatis.mapperScanBasePackage";

    public MybatisMapperScannerConfiguration() {
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage(this.getScanPackage());
        return configurer;
    }

    private String getScanPackage() {
        String scanPackage = "";
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = MybatisMapperScannerConfiguration.class.getResourceAsStream("/application.properties");
            properties.load(inputStream);
            scanPackage = properties.getProperty("spring.mybatis.mapperScanBasePackage").trim();
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception var12) {
                    var12.printStackTrace();
                }
            }

        }

        return scanPackage;
    }
}
