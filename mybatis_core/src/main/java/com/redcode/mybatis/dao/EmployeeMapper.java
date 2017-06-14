package com.redcode.mybatis.dao;

import com.redcode.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Erichou on 6/14/17.
 */
public interface EmployeeMapper {

    List<Employee> getEmpsByLastNameLike(String lastName);

    Employee getEmployeeById(Integer id);

    Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    //返回一条记录的map:key是列名,值就是对应值
    Map<String,Object> getEmpMapById(Integer id);
    //指明封装map的时候使用哪个属性
    @MapKey("id")
    Map<Integer,Employee> getEmpsMapsByLastNameLike(String lastName);

    Integer addEmployee(Employee employee);

    boolean deleteEmployeeById(Integer id);

    void updateEmployee(Employee employee);
}
