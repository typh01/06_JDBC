package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample5 {
	public static void main(String[] args) {
		// 부서명을 입력 받아
		// 해당 부서의 근무하는 사원의 
		// 사번, 이름, 부서명, 직급명을 
		// 직급코드 내림 차순으로 조회
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB 연결 정보
			String type = "jdbc:oracle:thin:@";
			String host = "112.221.156.34";		
			String port = ":12345";
			String dbName = ":XE";
			String userName= "KH12_PTH";
			String password = "KH1234";
			
			conn = DriverManager.getConnection(
					type + host + port + dbName,
					userName, 
					password
				);
			
			// 범위 입력 받기
			System.out.println("=== 부서명 입력 받기 ===");
			
			System.out.print("부서명 입력 : ");
			String deptName = sc.next();
			
		
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, J.JOB_NAME ");
			sb.append("FROM EMPLOYEE E ");
			sb.append("LEFT JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID) ");
			sb.append("JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE) ");
			sb.append("WHERE D.DEPT_TITLE = '");
			sb.append(deptName);
			sb.append("' ORDER BY J.JOB_CODE ASC ");
			
			String sql = sb.toString();
			
			/*
			 * // """ """ : 작성된 문자열 형태 그대로 저장
			 * 
			 * String sql = String.format(""" 
			 * SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, J.JOB_NAME 
			 * FROM EMPLOYEE E 
			 * JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE) 
			 * JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID) 
			 * WHERE DEPT_TITLE = '%s' 
			 * ORDER BY E.JOB_CODE ASC """, input);
			 */
			
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptCode = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				
				System.out.printf(
						"%s || %s || %s || %s \n",
						empId, empName, deptCode, jobName);
			}
			
		}catch (Exception e) { 
			e.printStackTrace();
		
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
