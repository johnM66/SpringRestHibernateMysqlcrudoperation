package controller;

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
	public void createEmployee(Employee emp) {
		empDao.createEmployee(emp);
		
	}

	@Override
	public void updateEmployee(Employee emp) {
		empDao.updateEmployee(emp);
		
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
