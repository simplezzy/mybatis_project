package com.redcode.mybatis.dao;

import com.redcode.mybatis.bean.Employee;

/**
 * Created by Erichou on 6/14/17.
 */
public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

}
