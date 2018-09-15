package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bofei
 * @date 2018/9/14 17:05
 */
@Configuration
@MapperScan("com.example.demo.mapper*")
public class DataSourceConfig {

    @Bean("db1")
    @ConfigurationProperties(prefix = "ds1")
    public DataSource db1() {
        return DataSourceBuilder.create().build();
    }
    @Bean("db2")
    @ConfigurationProperties(prefix = "ds2")
    public DataSource db2() {
        return DataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties(prefix = "ds3")
    public DataSource db3() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2,
                                         @Qualifier("db3") DataSource db3) {
        DynamicDataSource dds = new DynamicDataSource();
        Map targetDataSources = new HashMap();
        targetDataSources.put("db1", db1);
        targetDataSources.put("db2", db2);
        targetDataSources.put("db3", db3);
        dds.setTargetDataSources(targetDataSources);
        dds.setDefaultTargetDataSource(db1);
        return dds;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(db1(),db2(),db3()));
//        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));

        return sqlSessionFactory.getObject();
    }

}
