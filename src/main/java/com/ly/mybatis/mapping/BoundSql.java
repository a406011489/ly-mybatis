package com.ly.mybatis.mapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 表示动态生成的SQL语句以及相应的参数信息
 */
@Getter
@Setter
@ToString
public class BoundSql {

    /**
     * 解析过后的sql
     */
    private String sqlText;

    /**
     * 参数集合
     */
    private List<ParameterMapping> parameterMappingList;

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }
}
