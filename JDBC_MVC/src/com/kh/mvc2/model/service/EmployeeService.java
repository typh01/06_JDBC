package com.kh.mvc2.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.util.JdbcUtil;
import com.kh.mvc2.model.dao.EmployeeDAO;
import com.kh.mvc2.model.dto.EmployeeDTO;

public class EmployeeService {
	
	private EmployeeDAO emDao = new EmployeeDAO();
	
	public List<EmployeeDTO> EmSelect(){
		
		Connection conn = JdbcUtil.getConnection();
		
		List<EmployeeDTO> list = emDao.EmSelect(conn);
		
		return list;
	}
	
	
}
