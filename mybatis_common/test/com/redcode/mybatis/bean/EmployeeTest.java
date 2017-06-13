package com.redcode.mybatis.bean;

import com.redcode.mybatis.dao.EmployeeDao;
import com.redcode.mybatis.dao.EmployeeDaoAnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.InputStream;

/**
 * Created by Erichou on 6/11/17.
 */
public class EmployeeTest {

    /*
    * 1.根据xml配置文件(全局配置文件)创建SqlSessionFactory对象,有数据源一些运行环境信息
    * 2.sql映射文件:配置每一个sql,以及sql的封装规则等
    * 3.将sql映射文件注册在全局文件中
    * 4.code:
    *    1.根据全局配置文件得到SqlSessionFactory;
    *    2.根据sqlSession工厂, 获取sqlSession instance,能直接执行已经映射的sql语句
    *        一个sqlSession就是代表和数据库的一次会话,用完关闭;
    *    3.使用sql的唯一标示(通常 namespace + id 标注唯一性)告诉MyBatis执行哪一个sql,sql都是保存在映射文件中的;
    * */

    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config_tag.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
     public void connectionTest() throws Exception{
         SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

         SqlSession openSession = sqlSessionFactory.openSession();
        try {
            Employee employee = openSession.selectOne("com.redcode.mybatis.dao.EmployeeDao.selectEmp",1000);
            System.out.println("employee:" + employee.toString());
        } finally {
            openSession.close();
        }
     }

    /*1. 接口式编程
    *   1.原生: Dao ==== DaoImpl
    *     mybatis:  Mapper ===> xxMapper.xml
    *
    *   2.SqlSession代表和数据库的一次会话,用完必须关闭
    *   3.SqlSession和connection一样都是非线程安全的;所以每次使用都必须重新获取新的对象
    *   4.mapper接口没有实现类,但是mybatis 会为这个接口生成一个代理对象
    *     将接口和xml绑定
    *
    *   5.两个重要的配置文件
    *     mybatis 的全局文件:包含数据库连接池信息,事务管理信息,系统运行环境信息等
    *     sql映射文件:保存每一个sql语句的映射信息:
    *
    *   */
    @Test
    public void bindInterfaceTest() throws Exception {
        //1.获取sqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        //3.获取接口的实现类,会为接口自动的创建一个proxy对象,由代理对象执行CRUD
        try {
            EmployeeDao employeeDao = openSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.getEmployeeById(1000);
            System.out.println("Employee:" + employee.toString());
        }finally {
            openSession.close();
        }
    }

    @Test
    public void bindInterface0Test() throws Exception {
        //1.获取sqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        //3.获取接口的实现类,会为接口自动的创建一个proxy对象,由代理对象执行CRUD
        try {
            EmployeeDaoAnotation employeeDao = openSession.getMapper(EmployeeDaoAnotation.class);
            Employee employee = employeeDao.getEmployeeById(1000);
            System.out.println("Employee:" + employee.toString());
        }finally {
            openSession.close();
        }
    }



}