package com.meepo.mybatis.employee.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meepo.mybatis.employee.Employee;
import com.meepo.mybatis.employee.EmployeeExample;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    List<Employee> selectByExampleWithDept(EmployeeExample example);

    List<Employee> getEmps();
    
    Employee selectByPrimaryKeyWithDept(Integer id);
    
}