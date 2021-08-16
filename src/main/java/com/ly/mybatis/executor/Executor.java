package com.ly.mybatis.executor;

import com.ly.mybatis.mapping.MappedStatement;
import com.ly.mybatis.session.Configuration;

import java.util.List;

/**
 * MyBatis执行器，是MyBatis调度的核心，负责SQL语句的生成和查询缓存的维护
 *
 * 该类的主要作用是：
 * 1、根据传递的参数，完成SQL语句的动态解析，生成BoundSql对象，供StatementHandler使用；
 * 2、为查询创建缓存，以提高性能
 * 3、创建JDBC的Statement连接对象，传递给*StatementHandler*对象，返回List查询结果。
 */
public interface Executor {
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] param) throws Exception;
}
