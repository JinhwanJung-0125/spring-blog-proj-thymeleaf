package com.springblogproj.config;//package com.blog.springblogproj.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import javax.sql.DataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class DataSourceConfig {
//    @Bean(value = "myBatisDataSource", destroyMethod = "close")
//    public DataSource myBatisDataSource(Environment environment) {
//        HikariConfig hikariConfig = new HikariConfig();
//
//        hikariConfig.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
//        hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
//        hikariConfig.setUsername(environment.getProperty("spring.datasource.username"));
//        hikariConfig.setPassword(environment.getProperty("spring.datasource.password"));
//        hikariConfig.setPoolName(environment.getProperty("spring.datasource.hikari.pool-name"));
//        hikariConfig.setMinimumIdle(Integer.parseInt(environment.getProperty("spring.datasource.hikari.minimum-idle")));
//        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("spring.datasource.hikari.maximum-pool-size")));
//        hikariConfig.setConnectionTimeout(Long.parseLong(environment.getProperty("spring.datasource.hikari.connection-timeout")));
//        hikariConfig.setValidationTimeout(Long.parseLong(environment.getProperty("spring.datasource.hikari.validation-timeout")));
//        hikariConfig.setIdleTimeout(Long.parseLong(environment.getProperty("spring.datasource.hikari.idle-timeout")));
//
//        return new HikariDataSource(hikariConfig);
//    }
//
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory getSqlSessionFactory(DataSource myBatisDataSource, ApplicationContext applicationContext) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//    }
//}
