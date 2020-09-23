package com.blazeclan.jdbc.springbootwithjdbc.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.blazeclan.jdbc.springbootwithjdbc.entities.Employee;
import com.blazeclan.jdbc.springbootwithjdbc.entities.EmployeeRowMapper;

@Repository
public class EmployeeRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmp() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select id,firstname,lastname,phone,address,email_id from employee;",new EmployeeRowMapper());
	}
	/*
	 * getting all employee personal details and match professional details
	 */
	public List<Employee> getAllEmpmatchidemppro(){
		return jdbcTemplate.query("select * from employee left join emp_pro on employee.id=emp_pro.id;", new EmployeeRowMapper());
	}
	
	/*
	 * Getting all personal and professional details of employee who's id is matching. 
	 */
	public List<Employee> getAllEmpbyEmpproid(){
		return jdbcTemplate.query("select * from employee inner join emp_pro on employee.id=emp_pro.id;",new EmployeeRowMapper());
		
	}
	
	public Employee findById(Integer id) {
		
		String sql = "select * from employee where id=?";
		try {
			return (Employee) this.jdbcTemplate.queryForObject(sql, new Object[] {id}, new EmployeeRowMapper());
			
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	public Boolean CreateEmp(Employee Employee) {
		// TODO Auto-generated method stub
		String query="insert into employee values(?,?,?,?,?,?);";
		 jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){
	            @Override
	            public Boolean doInPreparedStatement(PreparedStatement ps)
	                    throws SQLException, DataAccessException {

	                ps.setInt(1,Employee.getId());
	                ps.setString(2,Employee.getFirstname());
	                ps.setString(3,Employee.getLastname());
	                ps.setLong(4,Employee.getPhone());
	                ps.setString(5,Employee.getAddress());
	                ps.setString(6,Employee.getEmail_id());
	                /*
	                 * New table entry .....
	                 */
//	                Employee_Pro Employee_Pro = new Employee_Pro();
//	                ps.setString(1,Employee_Pro.getDesignation());
//	                ps.setString(2,Employee_Pro.getEmail());
//	                ps.setString(3,Employee_Pro.getDept());
//	                ps.setString(4,Employee_Pro.getWork_exp());
//	                ps.setString(5,Employee_Pro.getReporting_to());
//	                ps.setDate(6, Employee_Pro.getJoinning_date());
	                
	                return ps.execute();

	            }
	        });
		   
		 String query1 = "insert into emp_pro values((select max(id) from employee),?,?,?,?,?,?);";
		   return jdbcTemplate.execute(query1 ,new PreparedStatementCallback<Boolean>(){
	            @Override
	            public Boolean doInPreparedStatement(PreparedStatement ps)
	                    throws SQLException, DataAccessException {

//	            	Employee_Pro Employee_Pro = new Employee_Pro();
	            	
//	                ps.setInt(1,Employee_Pro.getId());
	                ps.setString(1,Employee.getDesignation());
	                ps.setString(2,Employee.getEmail());
	                ps.setString(3,Employee.getDept());
	                ps.setString(4,Employee.getWork_exp());
	                ps.setString(5,Employee.getReporting_to());
	                ps.setDate(6, Employee.getJoinning_date());
	                
	                return ps.execute();

	            }
	        });
	} 

	public Integer UpdateEmp(Employee Employee) {
		// TODO Auto-generated method stub
		  String query="update employee set firstname = ? , lastname = ? , phone = ? , address = ? , email_id = ? where id = ?";
	        Object[] params = {Employee.getFirstname(), Employee.getLastname(),Employee.getPhone(),Employee.getAddress(),Employee.getEmail_id(),Employee.getId()};
	        int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};

	        return jdbcTemplate.update(query, params, types);
	}
	
	public Integer deleteEmpById(Integer id){
        return jdbcTemplate.update("delete from employee where id = ?",id);
    }
	
	/*
	 * Gtting all professional information with matching personal
	 */
	public List<Employee> GetAllEmppromatchgetEmp() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select *, designation from employee right join emp_pro on employee.id=emp_pro.id;",new EmployeeRowMapper());
	}
	
}
