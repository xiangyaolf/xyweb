//package com.zhst.config.datasource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * 火电数据源配置
// */
//@Configuration
//@MapperScan(basePackages = MonitorDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "monitorSqlSessionFactory")
//public class MonitorDataSourceConfig {
//
//    //连接火电数据库Mapper的包路径
//    static final String PACKAGE = "com.zhst.dao.monitor";
//
//    @Bean(name = "monitorDatasource")
//    @ConfigurationProperties(prefix = "monitor.datasource")
//    @Primary
//    public DataSource monitorDatasource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean(name = "monitorTransactionManager")
//    @Primary
//    public DataSourceTransactionManager monitorTransactionManager() {
//        return new DataSourceTransactionManager(monitorDatasource());
//    }
//
//    @Bean(name = "monitorSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory monitorSqlSessionFactory(@Qualifier("monitorDatasource") DataSource monitorDatasource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(monitorDatasource);
//        sessionFactory.setConfiguration(getMonitorConfiguration());
//        return sessionFactory.getObject();
//    }
//
//    /**
//     * 设置Mybatis全局相关属性
//     *
//     * @return
//     */
//    private org.apache.ibatis.session.Configuration getMonitorConfiguration() {
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setCallSettersOnNulls(true);
//        configuration.setMapUnderscoreToCamelCase(true);
//        return configuration;
//    }
//}
