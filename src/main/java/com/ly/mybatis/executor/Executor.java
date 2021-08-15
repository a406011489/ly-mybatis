package com.ly.mybatis.executor;

import com.ly.mybatis.config.Configuration;
import com.ly.mybatis.mapped.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    <E> List<E> query(Configuration configuration,
                      MappedStatement mappedStatement,
                      Object[] param) throws Exception;

    void close() throws SQLException;

}
