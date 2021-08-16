package com.ly.mybatis.mapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * org.apache.ibatis.mapping.ParameterMapping
 */
@Getter
@Setter
@ToString
public class ParameterMapping {
    private String content;
    public ParameterMapping(String content) {
        this.content = content;
    }
}
