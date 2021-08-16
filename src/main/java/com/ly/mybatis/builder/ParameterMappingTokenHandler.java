package com.ly.mybatis.builder;

import com.ly.mybatis.mapping.ParameterMapping;
import com.ly.mybatis.parsing.TokenHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责将匹配到的 #{ 和 } 对，
 * 替换成相应的“?”占位符，并获取该“?”占位符对应的 org.apache.ibatis.mapping.ParameterMapping 对象。
 *
 * 在源码中是一个org.apache.ibatis.builder.SqlSourceBuilder中的内部类
 */
@Getter
@Setter
@ToString
public class ParameterMappingTokenHandler implements TokenHandler {

	private List<ParameterMapping> parameterMappings = new ArrayList<>();

	// context是参数名称 #{id} #{age}
	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	private ParameterMapping buildParameterMapping(String content) {
		return new ParameterMapping(content);
	}
}
