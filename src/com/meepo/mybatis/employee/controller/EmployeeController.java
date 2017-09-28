package com.meepo.mybatis.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meepo.mybatis.employee.Employee;
import com.meepo.mybatis.employee.EmployeeExample;
import com.meepo.mybatis.employee.service.EmployeeService;
import com.meepo.mybatis.utils.JedisPoolUtil;
import com.meepo.mybatis.utils.Msg;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/getemps")
	public String emps(Map<String, Object> map) {
		/*
		 * List<Employee> emps = employeeService.getEmps(); map.put("allEmps",
		 * emps);
		 */
		return "index";
	}

	@RequestMapping("/toUsers")
	public String toUsers(Map<String, Object> map) {
		/*
		 * List<Employee> emps = employeeService.getEmps(); map.put("allEmps",
		 * emps);
		 */
		return "list";
	}

	@ResponseBody
	@RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
	public Msg deleteEmps(@PathVariable("ids") String ids) {
		System.out.println(ids);
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		} else {
			int id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	 * public Msg deleteEmpById(@PathVariable("id") Integer id) {
	 * employeeService.deleteEmp(id); return Msg.success(); }
	 */

	/*
	 * 直接发送ajax=PUT形式的请求 封装的数据 Employee [id=103, lastName=null, gender=null,
	 * email=null, dId=null, department=null]
	 * 
	 * 问题： 请求体重有数据，但是Employe对象封装不上
	 * 
	 * 原因： Tomcat 将请求体中的数据，封装一个map。 request.getParameter("empName")就会从这个map中取值
	 * springMVC封装POJO对象的时候，会把POJO中每个属性的值，request.getParameter("empName");
	 * ajax发送PUT请求引发的血案 PUT请求，请求体中的数据，request.getParameter("empName")拿不到
	 * Tomcat一看是PUT不会封装请求体中的数据为map，只有post形式的请求才封装请求体为map
	 * org.apache.catalina.connector.Request --parseParameters() (3114行);
	 * protected String parseBodyMethods = "POST";
	 * if(!getConnector.isParseBodyMethod(getMethod()){ success = true; return;
	 * }
	 * 
	 * 解决方案 我们要能支持直接发送PUT之类多的请求还要封装请求体中的数据 配置上HttpPutFormContentFilter
	 * 作用：将请求体中的数据解析包装成一个map，request被重新包装，request.getParameter()被重写，
	 * 就会从自己封装的map中取数据
	 * 
	 * 员工更新方法
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
	public Msg saveEmp(Employee employee) {
		System.out.println("将要更新的员工数据：" + employee.toString());
		employeeService.updateEmp(employee);
		return Msg.success();
	}

	/*
	 * 根据Id查询员工
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}

	/*
	 * 校验用户名是否可用
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuse(@RequestParam("empName") String name) {
		// 先判断用户名是否是合法的表达式
		String regx = "(^[a-z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		boolean matches = name.matches(regx);
		if (!matches) {
			return Msg.failed().add("va_msg", "用户名必须是6-16位数字或字母的组合或2-5位中文");
		}
		boolean b = employeeService.checkuser(name);
		if (b) {
			return Msg.success();
		} else {
			return Msg.failed().add("va_msg", "用户名不可用");
		}
	}

	/*
	 * 1、支持jsr303
	 * 
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			// 校验失败。应该返回失败，在模态框中显示校验失败的错误信息;
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.failed().add("errorField", map);
		} else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}

	}

	public Msg saveRedis(String name) {
		JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.sismember("success", name) || jedis.sismember("failed", name)) {
				return Msg.failed();
			} else {
				if (jedis.scard("success") < 2) {
					jedis.sadd("success", name);
					System.out.println("保存成功！");
					return Msg.success();
				} else {
					jedis.sadd("failed", name);
					System.out.println("保存失败！");
					return Msg.failed();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			JedisPoolUtil.release(jedisPool, jedis);
		}
		return null;
	}

	@RequestMapping("/test")
	@ResponseBody
	public Msg Test(@RequestParam("inputName") String name) {
		System.out.println(name);
		Msg msg = saveRedis(name);
		return msg;
	}

	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 这不是一个分页查询；
		// 引入pagehelper分页插件
		// 查询之前只需要调用,传入页码以及每页的大小
		Page<Object> page = PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询

		EmployeeExample example = new EmployeeExample();
		example.setOrderByClause("e.id");
		List<Employee> employees = employeeService.getAll(example);
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息，包括有我们查询出来的数据。传入连续显示的页数
		PageInfo<Employee> pageInfo = new PageInfo<>(employees,5);
		//or PageInfo pageInfo = new PageInfo(employees, 5);
		return Msg.success().add("pageInfo", pageInfo);

	}
}
