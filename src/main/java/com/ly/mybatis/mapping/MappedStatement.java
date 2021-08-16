package com.ly.mybatis.mapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 该类维护了一条＜select | update | delete | insert＞节点的封装
 *
 * 这个 MappedStatement 类就相对应一个mapper文件里的 select 语句
 * 当然也可以是update等，包含了id，sql语句，接收参数，返回参数等
 */
@Getter
@Setter
@ToString
public class MappedStatement {
    // id
    private String id;
    // sql语句
    private String sql;
    // 返回值类型
    private String resultType;
    // 参数值类型
    private String paramterType;
}
