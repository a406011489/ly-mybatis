package com.ly.mybatis.session.defauls;

import com.ly.mybatis.executor.SimpleExecutor;
import com.ly.mybatis.session.SqlSession;
import com.ly.mybatis.session.SqlSessionFactory;
import com.ly.mybatis.session.Configuration;

/**
 * SqlSession是MyBatis中用于和数据库交互的顶层类，
 * 通常将它与ThreadLocal绑定，一个会话使用一 个SqlSession,
 * 并且在使用完毕后需要close
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession(){
        return new DefaultSqlSession(configuration, new SimpleExecutor());
    }
}

