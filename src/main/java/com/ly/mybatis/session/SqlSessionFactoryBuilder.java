package com.ly.mybatis.session;

import com.ly.mybatis.session.defauls.DefaultSqlSessionFactory;
import com.ly.mybatis.builder.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 该类主要的作用是是解析出 Configuration 配置对象，
 * 相当于将xml文件里的内容变成Java对象去处理。
 *
 * org.apache.ibatis.session.SqlSessionFactoryBuilder
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws Exception {

        // 1.创建一个XMLConfigBuilder将xml流解析成为Configuration对象
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();

        // 2.创建 sqlSessionFactory
        Configuration configuration = xmlConfigBuilder.parseConfiguration(inputStream);

        // 3.创建 sqlSessionFactory
        return new DefaultSqlSessionFactory(configuration);
    }
}
