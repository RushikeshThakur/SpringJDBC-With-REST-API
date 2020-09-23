package com.blazeclan.jdbc.springbootwithjdbc.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blazeclan.jdbc.springbootwithjdbc.entities.Employee;
import com.blazeclan.jdbc.springbootwithjdbc.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository EmployeeRepository;
	
	@GetMapping("welcome")
	public String Testing() {
		return "Welocme to spring boot jdbc";
	}
	
	@GetMapping("employees")
	public List<Employee> getAllEmp(){
		return EmployeeRepository.getAllEmp();
		
	}
	
	@GetMapping("employee/inner")
	public List<Employee> getInnerJoin(){
		return EmployeeRepository.getAllEmpbyEmpproid();
	}
	
	@GetMapping("employee/left")
	public List<Employee> getLeftJoin(){
		return EmployeeRepository.getAllEmpmatchidemppro();
	}
	
	@GetMapping("employee/right")
	public List<Employee> getRightJoin(){
		return EmployeeRepository.GetAllEmppromatchgetEmp();
	}
	
	@GetMapping("employee/{id}")
	public ResponseEntity<?> getEmp(@PathVariable("id") Integer id){
		
		Employee Employee = EmployeeRepository.findById(id);
		if(Employee == null) {
			return new ResponseEntity<String>("No user found with this"+ "=" +id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(Employee, HttpStatus.OK);
	}
	
	@PostMapping("employee/create")
	public ResponseEntity<String> CreateEmp(@RequestBody Employee Employee) throws SQLIntegrityConstraintViolationException{
		if(EmployeeRepository.findById(Employee.getId()) != null) { // id not = to null form database
			return new ResponseEntity<String>("Record already exists wit id=" + Employee.getId(), HttpStatus.IM_USED);
		}
		EmployeeRepository.CreateEmp(Employee);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}	
	
	@PutMapping("employee/update")
	public ResponseEntity<?> UpdateEmp(@RequestBody Employee Employee){
		if(EmployeeRepository.findById(Employee.getId()) == null) {
			return new ResponseEntity<String>("No record present for update for id="+ Employee.getId(),HttpStatus.NOT_FOUND);
		}
		EmployeeRepository.UpdateEmp(Employee);
		return new ResponseEntity<Employee>(Employee, HttpStatus.OK);
	}
	
	@DeleteMapping("employee/delete/{id}")
	public ResponseEntity<?> DeleteEmp(@PathVariable("id") Integer id){
		Employee Employee = EmployeeRepository.findById(id);
		if(Employee == null) {
			return new ResponseEntity<String>("Record can't deleted as id="+ Employee.getId(), HttpStatus.NOT_FOUND);
		}
		EmployeeRepository.deleteEmpById(id);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}

}
