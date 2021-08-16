package com.ly.mybatis.session;

import java.util.List;

/**
 * 该类拥有所有的执行语句、提交或回滚事务和获取映射器实例的方法。
 * 作为MyBatis工作的主要顶层API，表示和数据库交互的会话，完成必要数据库增删改查功能。
 *
 */
public interface SqlSession {

    <E> List<E> selectList(String statementId, Object... param) throws Exception;

    <T> T selectOne(String statementId, Object... params) throws Exception;

    //为Dao接口生成代理实现类
    <T> T getMapper(Class<?> mapperClass);

}
