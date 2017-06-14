package com.redcode.mybatis.dao;

import com.redcode.mybatis.bean.Employee;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Erichou on 6/14/17.
 */
public class EmployeeMapperTest extends TestCase {

    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 增删改test
     * 1.mybatis允许增删改直接定义不同的类型返回值
     *   Integer/Long/Boolean
     * 2.需要手动提交
     * */
    @Test
    public void testAddEmployee() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //1.获取到的sqlSession不会自动提交
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null,"Lily","lily@163.com", "0");
            Integer temp = mapper.addEmployee(employee);
            System.out.println("return value:" + temp + ";increased id:" + employee.getId());
            //2.手动提交
            openSession.commit();
        } finally {
            openSession.close();
        }

    }

    public void testDeleteEmployeeById() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            employeeMapper.deleteEmployeeById(1);
            openSession.commit();
        } finally {
            openSession.close();
        }

    }

    public void testUpdateEmployee() throws Exception {
        SqlSession openSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            mapper.updateEmployee(new Employee(1,"Lucy","lucy@163.com", "1"));
            openSession.commit();
        } finally {
            openSession.close();
        }

    }
    //多参数查询
    public void testGetEmployeeByIdAndLastName() throws Exception {
        SqlSession openSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeByIdAndLastName(1001,"Lucy");
            System.out.println("employee:" + employee.toString());
        } finally {
            openSession.close();
        }
    }

    //返回多条记录
    public void testGetEmpsByLastNameLike() throws Exception{
        SqlSession openSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.getEmpsByLastNameLike("%y%");
            for(Employee employee : employees) {
                System.out.println("employee:" + employee.toString());
            }

        } finally {
            openSession.close();
        }
    }

    //返回map类型
    public void testGetEmpMapById() throws Exception {
        SqlSession openSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //单记录map
            Map<String,Object> map = mapper.getEmpMapById(1003);
            System.out.println("empMap:" + map);
            //多记录的map
            Map<Integer,Employee> mapEmps = mapper.getEmpsMapsByLastNameLike("%y%");
            System.out.println("mapEmps:" + mapEmps);

        } finally {
            openSession.close();
        }
    }

}