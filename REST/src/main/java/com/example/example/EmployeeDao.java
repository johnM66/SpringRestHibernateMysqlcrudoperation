package com.example.example;

import java.util.List;



public interface EmployeeDao {
public Employee createEmployee(Employee emp);
public Employee updateEmployee(Employee emp);
public void deleteEmployeee(int id);
public Employee getEmployeeId(int id);
public List<Employee> getAllEmployee();


}
