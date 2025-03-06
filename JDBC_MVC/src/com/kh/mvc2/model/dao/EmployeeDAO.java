package com.kh.mvc2.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.util.JdbcUtil;
import com.kh.mvc2.model.dto.EmployeeDTO;

public class EmployeeDAO {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc 파일을 확인!");
		}
	}
	
	public List<EmployeeDTO> EmSelect(Connection conn){
		
    List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();

    String sql = """
    		SELECT * 
    		FROM EMPLOYEE
    		""";

    PreparedStatement pstmt = null;
    ResultSet rset = null;

    try {
        pstmt = conn.prepareStatement(sql);
        rset = pstmt.executeQuery();

        while(rset.next()) {
            EmployeeDTO employee = new EmployeeDTO();
            employee.setEmpId(rset.getString("EMP_ID"));
            employee.setEmpName(rset.getString("EMP_NAME"));
            employee.setEmpNo(rset.getString("EMP_NO"));
            employee.setEmail(rset.getString("EMAIL"));
            employee.setPhoneNo(rset.getString("PHONE"));
            employee.setDeptCode(rset.getString("DEPT_CODE"));
            employee.setJobCode(rset.getString("JOB_CODE"));
            employee.setSalLev(rset.getString("SAL_LEVEL"));
            employee.setSalary(rset.getInt("SALARY"));
            employee.setBonus(rset.getInt("BONUS"));
            employee.setManagerId(rset.getString("MANAGER_ID"));
            employee.setHireDate(rset.getDate("HIRE_DATE"));
            employee.setEntDate(rset.getDate("ENT_DATE"));
            employee.setEntYN(rset.getString("ENT_YN"));
            list.add(employee);
        }
        
    } catch(SQLException e) {
        e.printStackTrace();
    } finally {
        JdbcUtil.close(rset, null, pstmt, null);
    }
    return list;
	}	
	
	
}
