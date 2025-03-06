package com.kh.mvc2.controller;

import java.util.List;

import com.kh.mvc2.model.dto.EmployeeDTO;
import com.kh.mvc2.model.service.EmployeeService;

public class EmployeeController {
	
	private EmployeeService emService = new EmployeeService();
	
	public List<EmployeeDTO> EmSelect(){
		return emService.EmSelect();
	}
	
	public int EmInsert() {
		int result = 0;
		return result;
	}
	
}
