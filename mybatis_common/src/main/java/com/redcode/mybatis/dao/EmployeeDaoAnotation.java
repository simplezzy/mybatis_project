package com.redcode.mybatis.dao;

import com.redcode.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Erichou on 6/11/17.
 */
public interface EmployeeDaoAnotation {

    @Select("select * from MB_EMPLOYEE where id = #{id}")
    public Employee getEmployeeById(Integer id);
}
