package lk.tharindu.ems.service;

import java.util.List;

import lk.tharindu.ems.model.Employee;

public interface EmployeeService {

		Employee save(Employee employee);
		List<Employee> fetchAllEmployee();
		Employee fetchEmployee(Employee employee);
}
