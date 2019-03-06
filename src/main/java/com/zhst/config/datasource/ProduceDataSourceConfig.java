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
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * 火电数据源配置
// */
//@Configuration
//@MapperScan(basePackages = ProduceDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "produceSqlSessionFactory")
//public class ProduceDataSourceConfig {
//    //连接火电数据库Mapper的包路径
//    static final String PACKAGE = "com.zhst.dao.produce";
//
//    @Bean(name = "produceDatasource")
//    @ConfigurationProperties(prefix = "produce.datasource")
//    public DataSource produceDatasource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean(name = "produceTransactionManager")
//    public DataSourceTransactionManager produceTransactionManager() {
//        return new DataSourceTransactionManager(produceDatasource());
//    }
//
//    @Bean(name = "produceSqlSessionFactory")
//    public SqlSessionFactory produceSqlSessionFactory(@Qualifier("produceDatasource") DataSource produceDatasource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(produceDatasource);
//        sessionFactory.setConfiguration(getProduceConfiguration());
//        return sessionFactory.getObject();
//    }
//
//    /**
//     * 设置Mybatis全局相关属性
//     * @return
//     */
//    private org.apache.ibatis.session.Configuration getProduceConfiguration() {
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setCallSettersOnNulls(true);
//        configuration.setMapUnderscoreToCamelCase(true);
//        return configuration;
//    }
//}
