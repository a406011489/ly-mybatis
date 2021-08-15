package com.ly.mybatis.builder;

import com.ly.mybatis.config.Configuration;
import com.ly.mybatis.executor.SimpleExecutor;
import com.ly.mybatis.mapped.MappedStatement;

import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.List;
import java.lang.reflect.*;


public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {

        // 将要去完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();

        /*
         * 在mappedStatement里拿到该sql，因为有传入进来的statementid，
         * 然后在statement里就可以拿到sql，id，参数类型和返回值类型等等的内容了
         */
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);

        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);

        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws Exception {

        List<Object> objects = selectList(statementid, params);

        if(objects.size()==1){
            return (T) objects.get(0);
        }else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理来为Dao接口生成代理对象，并返回
        Object proxyInstance = // 三个参数：用哪个类加载器去加载代理对象；动态代理类需要实现的接口；动态代理方法在执行时，会调用h里面的invoke方法去执行
                Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, (proxy, method, args) -> {

            // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
            // 方法名：findAll
            String methodName = method.getName();
            String className = method.getDeclaringClass().getName();

            // 用该国反射就拿到“类名.方法名”了。
            String statementId = className+"."+methodName;

            // 准备参数2：params:args
            // 拿到方法的返回值类型
            Type genericReturnType = method.getGenericReturnType();

            // 判断是否进行了泛型类型参数化，说白了，就看有没有<>，有的话就是List等集合类型。
            if(genericReturnType instanceof ParameterizedType){
                return selectList(statementId, args);
            }
            return selectOne(statementId,args);
        });
        return (T) proxyInstance;
    }
}