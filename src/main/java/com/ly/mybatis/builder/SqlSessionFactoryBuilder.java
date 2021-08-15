package com.ly.mybatis.builder;

import com.ly.mybatis.config.Configuration;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    private final Configuration configuration;

    public SqlSessionFactoryBuilder() {
        this.configuration = new Configuration();
    }

    public SqlSessionFactory build(InputStream inputStream) throws Exception {

        //1.解析配置文件，封装Configuration XMLConfigBuilder
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(configuration);

        Configuration configuration = xmlConfigBuilder.parseConfiguration(inputStream);

        //2.创建 sqlSessionFactory
        return new DefaultSqlSessionFactory(configuration);
    }
}
