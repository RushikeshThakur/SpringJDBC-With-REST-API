package com.blazeclan.jdbc.springbootwithjdbc.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

//import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;


//Converting result set to object & send back result
public class EmployeeRowMapper implements RowMapper {

	/*
	 * same data_type provided as Employee
	 */
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee Employee = new Employee();
		Employee.setId(rs.getInt("id"));
		Employee.setFirstname(rs.getString("firstname"));
		Employee.setLastname(rs.getString("lastname"));
		Employee.setPhone(rs.getLong("phone"));
		Employee.setAddress(rs.getString("address"));
		Employee.setEmail_id(rs.getString("email_id"));
		Employee.setDesignation(rs.getString("designation"));
		Employee.setEmail(rs.getString("email"));
		Employee.setDept(rs.getString("dept"));
		Employee.setWork_exp(rs.getString("work_exp"));
		Employee.setReporting_to(rs.getString("reporting_to"));
		Employee.setJoinning_date(rs.getDate("joinning_date"));
	      return Employee;
	   }
}
