package com.ly.mybatis.utils;

import java.util.ArrayList;
import java.util.List;

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

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

}
