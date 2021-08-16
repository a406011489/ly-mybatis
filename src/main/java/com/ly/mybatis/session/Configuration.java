package com.ly.mybatis.session;

import com.ly.mybatis.mapping.MappedStatement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 这里的Configuration就相当于一个xml文件里的内容
 * 当然，真实的xml文件里包含了许多标签，比如：
 * properties (属性)，
 * settings (设置)，
 * typeAliases (类型别名)，
 * typeHandlers (类型处理器)，
 * objectFactory (对象工厂)，
 * mappers (映射器)等
 *
 */
@Getter
@Setter
@ToString
public class Configuration {

    // 数据源
    private DataSource dataSource;

    // map集合： key:statementId value:MappedStatement
    private final Map<String, MappedStatement> mappedStatementMap = new HashMap<>();
}
