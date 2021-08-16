package com.ly.mybatis.io;

import java.io.InputStream;

/**
 * 拿到xml等文件，将文件读取成一个流，
 * 相当于org.apache.ibatis.io.Resources类
 */
public class Resources {
    public static InputStream getResourceAsSteam(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
