package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DatabaseUtil {
    //获取执行sql的对象
    public static SqlSession getSqlSession() throws IOException {
        //获取配置的资源文件
        Reader reader= Resources.getResourceAsReader("datebaseConfig.xml");

        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
        //sqlSession即为可执行sql语句的对象
        SqlSession sqlSession=factory.openSession();
        return sqlSession;
    }
}
