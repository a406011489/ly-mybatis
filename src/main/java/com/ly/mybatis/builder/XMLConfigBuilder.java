package com.ly.mybatis.builder;

import com.ly.mybatis.session.Configuration;
import com.ly.mybatis.io.Resources;
import com.zaxxer.hikari.HikariDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 该类主要负责解析mybatis-config.xml配置文件。
 */
public class XMLConfigBuilder {

    private final Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfiguration(InputStream inputStream) throws Exception {

        Document document = new SAXReader().read(inputStream);

        Element rootElement = document.getRootElement();

        List<Element> propertyElements = rootElement.selectNodes("//property");

        Properties properties = new Properties();
        for (Element propertyElement : propertyElements) {
            String name = propertyElement.attributeValue("name");
            String value = propertyElement.attributeValue("value");
            properties.setProperty(name,value);
        }

        // 用“光”连接池
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(properties.getProperty("driverClass"));
        hikariDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        hikariDataSource.setUsername(properties.getProperty("user"));
        hikariDataSource.setPassword(properties.getProperty("password"));

        System.out.println(properties);

        //填充 configuration
        configuration.setDataSource(hikariDataSource);

        //mapper 部分
        List<Element> mapperElements = rootElement.selectNodes("//mapper");
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
        for (Element mapperElement : mapperElements) {
            String mapperPath = mapperElement.attributeValue("resource");
            InputStream resourceAsSteam =
                    Resources.getResourceAsSteam(mapperPath);
            xmlMapperBuilder.parse(resourceAsSteam);
        }
        return configuration;
    }
}
