package com.example.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Employee createEmployee(Employee emp) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if(emp!=null) {
			try {
				session.save(emp);
				transaction.commit();
				session.close();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}
		}
		return emp;
	}

	@Override
	public Employee getEmployeeId(int id) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Employee emp = new Employee();
		try {
			emp = session.get(Employee.class, id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return emp;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List listEmp=new ArrayList<Employee>();
		Session session = sessionFactory.openSession();
		listEmp=session.createQuery("from com.example.example.Employee").list();
		return listEmp;
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if(emp!=null) {
			try {
				session.update(emp);
				transaction.commit();
				session.close();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}
		}
		return emp;
	}

	@Override
	public void deleteEmployeee(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Employee emp = new Employee();
		try {
			emp = session.get(Employee.class, id);
			session.delete(emp);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
	}

}
