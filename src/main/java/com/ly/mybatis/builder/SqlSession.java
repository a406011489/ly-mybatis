package com.ly.mybatis.builder;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

    <E> List<E> selectList(String statementId, Object... param) throws Exception;
    <T> T selectOne(String statementId, Object... params) throws Exception;
    //为Dao接口生成代理实现类
    <T> T getMapper(Class<?> mapperClass);

}
