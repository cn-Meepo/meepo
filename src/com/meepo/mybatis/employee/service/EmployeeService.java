package com.meepo.mybatis.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meepo.mybatis.employee.Employee;
import com.meepo.mybatis.employee.EmployeeExample;
import com.meepo.mybatis.employee.EmployeeExample.Criteria;
import com.meepo.mybatis.employee.dao.EmployeeMapper;




@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	public List<Employee> getEmps() {
		return employeeMapper.getEmps();
	}
	/*
	 * 查询所有员工
	 * 
	 */

	public List<Employee> getAll(EmployeeExample example) {
		return employeeMapper.selectByExampleWithDept(example);
	}

	/*
	 * 保存员工
	 */

	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	//如果等于0 ，返回true；
	//否则返回false；
	public boolean checkuser(String name) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		// Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}
	//根据员工id查询员工
	public Employee getEmp(Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	/*
	 * 员工更新
	 * 
	 */

	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	/*
	 * 员工删除
	 * 
	 */
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}

	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		employeeMapper.deleteByExample(example);
		
	}
}
