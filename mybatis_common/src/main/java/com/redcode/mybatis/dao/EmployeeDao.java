package com.redcode.mybatis.dao;

import com.redcode.mybatis.bean.Employee;

/**
 * Created by Erichou on 6/11/17.
 */
public interface EmployeeDao {

    public Employee getEmployeeById(Integer id);
}
