package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_ATOM_XML_VALUE, consumes = MediaType.APPLICATION_ATOM_XML_VALUE)
	public ResponseEntity<Void> createEmployee(@RequestBody Employee emp) {
		employeeService.createEmployee(emp);
		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucb.path("create").buildAnd);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_ATOM_XML_VALUE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
		employeeService.deleteEmployeee(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_ATOM_XML_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		Employee employee = employeeService.getEmployeeId(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET, produces ="application/json")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> list = employeeService.getAllEmployee();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_ATOM_XML_VALUE, consumes = MediaType.APPLICATION_ATOM_XML_VALUE)
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
