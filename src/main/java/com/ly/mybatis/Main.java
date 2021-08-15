package com.ly.mybatis;

import com.ly.mybatis.builder.SqlSession;
import com.ly.mybatis.builder.SqlSessionFactory;
import com.ly.mybatis.builder.SqlSessionFactoryBuilder;
import com.ly.mybatis.config.Resources;
import com.ly.mybatis.entity.User;
import com.ly.mybatis.mapper.UserMapper;

import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
       User user = new User();
        user.setId("11");
        user.setAge(11);

         User user2 = sqlSession.selectOne("User.selectOne", user);

        System.out.println(user2);

        List<User> users = sqlSession.selectList("User.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }


        //代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User userl = userMapper.selectOne(user);
        System.out.println(userl);
    }
}
