package com.bolster.repository;

import org.springframework.data.repository.CrudRepository;

import com.bolster.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	Employee findByUserName(String userName);
}
