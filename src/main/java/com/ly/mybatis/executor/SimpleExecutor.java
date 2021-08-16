package com.ly.mybatis.executor;

import com.ly.mybatis.mapping.BoundSql;
import com.ly.mybatis.session.Configuration;
import com.ly.mybatis.mapping.MappedStatement;
import com.ly.mybatis.parsing.GenericTokenParser;
import com.ly.mybatis.mapping.ParameterMapping;
import com.ly.mybatis.builder.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {

        // 1. 拿到当前连接
        Connection connection = configuration.getDataSource().getConnection();

        // 2.拿到sql，并且进行处理，处理过后的BoundSql中就包含解析过后的sql(一系列问号)和问号所对应的参数。
        String sql = mappedStatement.getSql();
        BoundSql boundSql = this.getBoundSql(sql);

        // 3.获取预处理对象：preparedStatement，相当于JDBC参数，此时将带有问号的参数传给JDBC的PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 4.设置参数 获取到了参数的全路径
        String paramterType = mappedStatement.getParamterType();

        // 5.将它变成一个类
        Class<?> paramtertypeClass = this.getClassType(paramterType);

        // 6.取出参数集合
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();

        // 7.将参数集合一个一个封装进类
        for (int i = 0; i < parameterMappingList.size(); i++) {

            ParameterMapping p = parameterMappingList.get(i);

            String content = p.getContent();

            // 反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);

            // 暴力访问
            declaredField.setAccessible(true);

            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i + 1, o);
        }

        // 8.JDBC执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        // 9.同样拿到返回的类，并弄成一个类
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);

        ArrayList<Object> objects = new ArrayList<>();

        // 10.封装返回结果集
        while (resultSet.next()) {

            // 实例化成一个对象
            Object o = resultTypeClass.newInstance();

            /*
             * 获取表的元数据（通过ResultSetMetaData我们可以拿到该表的表结构,每个字段的类型，是否可为空值等）
             * 另外还有一个DatabaseMetaData，描述数据库的元数据对象。
             */
            ResultSetMetaData metaData = resultSet.getMetaData();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);

                /*
                 * 使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                 * 该类的主要方法：
                 * Class<?> getPropertyType() 获取属性的java类型对象
                 * Method getReadMethod() 获得用于读取属性值的方法
                 * Method getWriteMethod() 获得用于写入属性值的方法
                 * void setReadMethod(Method readMethod) Sets the method that should be used to read the property value.
                 * void setWriteMethod(Method writeMethod) Sets the method that should be used to write the property value.
                 */
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);

                // 获得用于写入属性值的方法
                Method writeMethod = propertyDescriptor.getWriteMethod();

                // 方法.(对象,值)，就可以把值赋到对象中了
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        return paramterType == null ? null : Class.forName(paramterType);
    }


    /**
     * 完成对#{}的解析工作：1.将#{}使用？进行代替，2.解析出#{}里面的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        // 标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler p = new ParameterMappingTokenHandler();

        GenericTokenParser g = new GenericTokenParser("#{", "}", p);
        // 解析出来的sql
        String parseSql = g.parse(sql);

        // #{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = p.getParameterMappings();

        return new BoundSql(parseSql, parameterMappings);
    }
}
