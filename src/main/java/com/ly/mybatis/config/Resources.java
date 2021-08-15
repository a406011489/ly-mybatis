package com.ly.mybatis.config;

import java.io.InputStream;

/**
 * 拿到xml等文件
 */
public class Resources {
    public static InputStream getResourceAsSteam(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
