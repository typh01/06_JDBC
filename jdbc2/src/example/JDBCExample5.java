package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample5 {
	public static void main(String[] args) throws ClassNotFoundException {
		
		// 아이디, 비밀번호, 새 비밀번호를 입력 받아
		// 아이디, 비밀번호가 일치하는 회원의 비밀번호 변경
		
		/* *** try-with-resources ***
		 * - try 선언 시 () 괄호 내에
		 * 	 try 구문에서 사용하고 반환할 자원을 선언하면
		 * 	 종료 시 자동으로 해당 자원을 
		 * 	 반환하는(close())하는 코드를 수행
		 * 	 --> 메모리 누수 방지 효과
		 * 
		 * 	(조건 : AutoCloseable을 구현한 객체만 자동 반환 가능)
		 * 
		 * - finally를 이용한 객체 자원 반환 구문 생략 가능!!
		 */
		String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		String userName = "KH12_PTH"; // 사용자 계정명
		String password = "KH1234"; // 계정 비밀번호
		
		Class.forName("oracle.jdbc.OracleDriver"); 
		
		String sql ="""
					UPDATE TB_USER SET
						USER_PW = ?
					WHERE
						USER_ID = ?
					AND
						USER_PW = ?
					""";
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
		
			conn.setAutoCommit(false);
			
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("--- 비밀번호 변경하기 ---");
			
			System.out.print("아이디 입력 : ");
			String id = sc.next();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.next();
			
			System.out.print("새 비밀번호 입력 : ");
			String newPw = sc.next();
			

			
			pstmt.setString(1,newPw);
			pstmt.setString(2,id);
			pstmt.setString(3,pw);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("비밀번호 변경 완료!!");
				conn.commit();
			} else {
				System.out.println("아이디 또는 비밀번호가 잘못 입력되었습니다.");
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
