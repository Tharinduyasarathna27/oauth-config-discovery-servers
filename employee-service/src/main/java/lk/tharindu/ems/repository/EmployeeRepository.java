package lk.tharindu.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lk.tharindu.ems.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
