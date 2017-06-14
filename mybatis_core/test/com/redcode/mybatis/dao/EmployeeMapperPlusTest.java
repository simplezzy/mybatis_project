package com.redcode.mybatis.dao;

import com.redcode.mybatis.bean.Employee;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by Erichou on 6/14/17.
 */
public class EmployeeMapperPlusTest extends TestCase{

    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testGetEmpById() throws Exception {
        SqlSession openSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapperPlus mapperPlus = openSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapperPlus.getEmpById(1002);
            System.out.println("employee:" + employee);

        } finally {
            openSession.close();
        }
    }

}