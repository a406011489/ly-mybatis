package com.ly.mybatis.config;

import com.ly.mybatis.mapped.MappedStatement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@ToString
public class Configuration {
    //数据源
    private DataSource dataSource;
    //map集合： key:statementId value:MappedStatement
    private final Map<String, MappedStatement> mappedStatementMap = new HashMap<>();
}
