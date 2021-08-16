package com.ly.mybatis;

import com.ly.mybatis.session.SqlSession;
import com.ly.mybatis.session.SqlSessionFactory;
import com.ly.mybatis.session.SqlSessionFactoryBuilder;
import com.ly.mybatis.io.Resources;
import com.ly.mybatis.entity.User;
import com.ly.mybatis.mapper.UserMapper;

import java.io.InputStream;
import java.util.List;

/**
 * 使用端
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // 使用mybatis的第一步，读取配置文件，该配置文件主要是数据库连接等信息，把配置文件读取成一个流
        InputStream stream = Resources.getResourceAsSteam("config.xml");

        // 创建出工厂后，直接就可以拿到sqlSession对象了
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId("11");
        user.setAge(11);

        // 通过普通的调用
        User user2 = sqlSession.selectOne("User.selectOne", user);

        System.out.println(user2);

        List<User> users = sqlSession.selectList("User.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }

        // 通过代理对象调用
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User userl = userMapper.selectOne(user);
        System.out.println(userl);
    }
}
