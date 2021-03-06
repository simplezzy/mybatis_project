package main.java.com.redcode.mybatis.bean;

import com.redcode.mybatis.bean.Employee;
import com.redcode.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by Erichou on 6/13/17.
 */
public class ConnectionTest {


    public SqlSessionFactory getSqlSessionFactory() throws Exception{

        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void connectionTest() throws Exception{

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getEmployeeById(1);
            System.out.println("Employee:" + employee.toString());
        } finally {
            openSession.close();
        }

    }




}