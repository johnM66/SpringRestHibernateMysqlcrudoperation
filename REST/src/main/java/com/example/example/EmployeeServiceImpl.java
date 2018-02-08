package com.example.example;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao empDao;
	
	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	@Override
	public Employee createEmployee(Employee emp) {
		Employee e=empDao.createEmployee(emp);
		return e;
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		Employee e=empDao.updateEmployee(emp);
		return e;
		
	}

	@Override
	public void deleteEmployeee(int id) {
		empDao.deleteEmployeee(id);
		
	}

	@Override
	public Employee getEmployeeId(int id) {
		return empDao.getEmployeeId(id);
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return empDao.getAllEmployee();
	}
}
