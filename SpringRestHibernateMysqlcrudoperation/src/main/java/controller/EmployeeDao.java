package controller;

import java.util.List;



public interface EmployeeDao {
public void createEmployee(Employee emp);
public void updateEmployee(Employee emp);
public void deleteEmployeee(int id);
public Employee getEmployeeId(int id);
public List<Employee> getAllEmployee();


}
