package com.example.example;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	//private static final Logger logger = LoggerFactor
	@Autowired
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Void> createEmployee(@RequestBody Employee emp) {
		employeeService.createEmployee(emp);
		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucb.path("create").buildAnd);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
		employeeService.deleteEmployeee(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		Employee employee = employeeService.getEmployeeId(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET,headers="Accept=*/*")
	public ResponseEntity<List<Employee>> getEmpAll() {
		HttpHeaders headers = new HttpHeaders();
		List<Employee> list = employeeService.getAllEmployee();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		} else {
			 headers.add("Number Of Records Found", String.valueOf(list.size()));
			return new ResponseEntity<List<Employee>>(list,headers, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Void> updateEmployee(@PathVariable("id") int id, @RequestBody Employee emp) {
		Employee employee = employeeService.getEmployeeId(id);

		if (employee == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			employee.setName(emp.getName());
			employee.setAge(emp.getAge());
			employeeService.updateEmployee(emp);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
