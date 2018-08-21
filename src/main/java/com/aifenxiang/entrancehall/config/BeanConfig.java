package com.aifenxiang.entrancehall.config;

import com.aifenxiang.foundation.configclass.BaseMybatisDao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author: zj
 * @create: 2018-08-21 21:49
 **/
@Configuration
public class BeanConfig {


    @Bean
    @ConfigurationProperties(prefix = "hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
       return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:sqlMapper/*.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    @Bean
    public BaseMybatisDao baseMybatisDao() throws Exception {
        return new BaseMybatisDao(sqlSessionFactory());
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("*Handler","*Service");
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        beanNameAutoProxyCreator.setInterceptorNames("transactionInterceptor");
        return beanNameAutoProxyCreator;
    }

    @Bean("transactionInterceptor")
    public TransactionInterceptor transactionInterceptor(){
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        Properties properties = new Properties();
        properties.setProperty("update*","PROPAGATION_REQUIRED");
        properties.setProperty("delete*","PROPAGATION_REQUIRED");
        properties.setProperty("insert*","PROPAGATION_REQUIRED");
        properties.setProperty("add*","PROPAGATION_REQUIRED");
        transactionInterceptor.setTransactionAttributes(properties);
        transactionInterceptor.setTransactionManager(dataSourceTransactionManager());
        return transactionInterceptor;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }


}
