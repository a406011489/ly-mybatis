package com.ly.mybatis.builder;

import com.ly.mybatis.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示动态生成的SQL语句以及相应的参数信息
 */
public class BoundSql {

    private String sqlText; //解析过后的sql

    private List<ParameterMapping> parameterMappingList;

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
