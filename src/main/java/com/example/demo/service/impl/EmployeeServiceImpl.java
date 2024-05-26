package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getSpecificEmployee(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		} else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if(optionalEmployee.isPresent()) {
			Employee existingEmployee = optionalEmployee.get();
			existingEmployee.setFirstName(employee.getFirstName());
			existingEmployee.setLastName(employee.getLastName());
			existingEmployee.setEmail(employee.getEmail());
			return employeeRepository.save(existingEmployee);
		} else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
	}

	@Override
	public void deleteEmployee(long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if(optionalEmployee.isPresent()) {
			employeeRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
	}
}
