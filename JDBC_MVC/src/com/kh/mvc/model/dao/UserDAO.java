package com.kh.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;

/**
 * DAO(Data Access Object)
 * 
 * 데이터베이스 관련된 작업(CRUD 를 목적으로)을 전문적으로 담당하는 객체
 * DAO안에 모든 메소드들은 데이터베이스와 관련된 기능으로 만들 것
 * 
 * Controller를 통해 호출된 기능을 수행
 * DB에 직접 접근한 후 SQL문을 수행하고 결과 받기 (JDBC)
 */
public class UserDAO {
	
	/*
	 * JDBC 용 객체
	 * 
	 * - Connection : DB 연결 정보를 담고 있는 객체(IP주소, Port, 사용자명, 비번)
	 * 
	 * - Statement : Connectino이 가지고 있는 연결정보 DB에
	 * 							 SQL문을 전달하고 실행하고 결과도 받아오는 객체
	 * 
	 * - ResultSet : 실행한 SQL문이 SELECT문일 경우 
	 * 							 조회된 결과가 처음 담기는 객체
	 * 
	 * - PreparedStatement : SQL문을 미리 준비하는 개념
	 * 											 ?(위치홀더)로 확보해놓은 공간을
	 * 											 사용자가 입력한 값들과 바인딩해서 SQL문을 수행
	 * 
	 * ** 처리 절차
	 * 
	 * 1) JDBC Driver 등록 : 해당 DBMS에서 제공하는 클래스를 동적으로 등록
	 * 
	 * 2) Connection 객체 생성 : 접속하고자하는 DB의 정보를 입력해서 생성
	 * 													(URL, 사용자이름, 비밀번호)
	 * 
	 * 3_1) PreparedStatement 객체 생성 : 
	 * 			Connection 객체를 이용해서 생성 (미완성된 SQL문을 미리 전달)
	 * 
	 * 3_2) 미완성된 SQL문을 완성형태로 만들어주어야함
	 * 	=> 미완성된 경우에만 해당 / 완성된 경우에는 생략
	 * 
	 * 4) SQL문을 실행 : excute<Query/Update>() => SQL을 인자로 전달하지 않음!
	 * 									 > DQL(SELECT) : excuteQuery()
	 * 									 > DML(INSERT / UPDATE / DELETE) : excuteUpdate()
	 * 
	 * 5) 결과 받기 : 
	 * 							> SELECT : ResultSet 타입 객체(조회 데이터 담김)
	 * 							> DML		 : int(처리된 행의 개수)
	 * 
	 * 6_1) ResultSet에 담겨있는 데이터들을 하나하나씩 뽑아서 DTO 객체 필드에
	 * 		옮겨담은 후 조회 결과가 여러 행일 경우 List로 관리
	 * 
	 * 6_2) 트랜잭션 처리(Commit / Rollback)
	 * 
	 * 7(생략될 수 있음) ) 자원 반납(close) => 생성의 역순으로 / nullpointerexception
	 * 
	 * 8) 결과 반환 -> Controller
	 * 									SELECT > 6_1에서 만든것 반환
	 * 									DML 	 > 처리된 행의 개수
	 * 
	 * + ACID
	 */
		
//		private final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
//		private final String USERNAME = "KH12_PTH";
//		private final String PASSWORD = "KH1234";
		
		
		static {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("ojdbc 파일을 확인해주세요.");
			}
		}
	
	public List<UserDTO> findAll(Connection conn){
		
		// DB로 이동!
		/* 전송 객체들
		 * 
		 * VO / DTO / Entity
		 * 
		 * 1명의 회원의 정보는 1개의 UserDTO객체의 필드에 값을 담아야겠다.
		 * 
		 * 문제점 : userDTO가 몇개가 나올지 알 수 없음
		 */
		
		// DB 와 File
		// 선택지는 4개 ( Array / List / Set / Map )
		// Array : 크기를 정해야하므로 X
		// Map : key가 없으므로 X
		// List 냐 Set 이냐
		// "순서" 필요하니 List O
		
//		Connection con = null;
//		
//		con.setAutoCommit(false);
		
		List<UserDTO> list = new ArrayList<UserDTO>(); // 참조형 변수 4byte 짜리
		
// 	*** Java는 무조건 값(기본자료형 8개 + 주소값) 9가지만 이동 ***
		
		String sql = "SELECT" 
									+   " USER_NO "
									+ ", USER_ID " 
									+ ", USER_PW"
									+ ", USER_NAME "
									+ ", ENROLL_DATE "
								+ " FROM "
										+ " TB_USER "
								+ " ORDER "
										+" BY "
											+ " ENROLL_DATE DESC";
		
//		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
//			conn = DriverManager.getConnection(
//					"jdbc::oracle:thin:@192.168.130.17:1521:xe",
//					URL, USERNAME, PASSWORD); // getConnection : Connection 을 반환해줌
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			// 최종목적은 view에 출력 - 위해서 결과를 존재하는지 조회해야함
			while(rset.next()) {
				// 조회 결과 컬럼 값을 DTO필드에 담는 작업 및 리스트에 요소로 추가
				UserDTO user = new UserDTO(); // 지역변수
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPw(rset.getString("USER_PW"));
				user.setUserName(rset.getString("USER_NAME"));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				list.add(user);
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("DB 서버 오류! 오타가 나지 않았나요?");
		} finally {
			JdbcUtil.close(rset, null, pstmt, conn);
		}
		
		return list ;
	}
	
	/**
	 * @param user : 사용자가 입력한 아이디 / 비밀번호 / 이름이 각각 필드에 대입되어있음
	 * @return 삽입 성공 여부 결과 int 로 반환
	 */
	public int insertUser(Connection conn, UserDTO user) {
		PreparedStatement pstmt = null;
		
		String sql = "INSERT "
							 	+ "INTO "
							 				+ "TB_USER "
							 	+ "VALUES "
							 				+ "("
							 				+ "SEQ_USER_NO.NEXTVAL"
							 			 +", ?"
							 			 +", ?"
							 			 +", ?"
							 			 +", SYSDATE"
							 				+")";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(null, null, pstmt, conn);
		}
		return result;	
	}

	/**
	 * @param user : 사용자가 입력한 아이디 / 비밀번호 / 이름이 각각 필드에 대입되어있음
	 * @return : 수정 성공 여부 
	 */
	public int modifyPw(Connection conn, UserDTO user, String newPw) {
		PreparedStatement pstmt = null;
		
		String sql = """
				UPDATE TB_USER SET
						USER_PW = ?
					WHERE
						USER_ID = ?
					AND
						USER_PW = ?
				""";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw);
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getUserPw());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(null, null, pstmt, conn);
		}
		return result;	
	}
	
	/**
	 * @param user : 사용자가 입력한 이름 / 아이디 / 비밀 번호 각각 필드에 대입되어있음
	 * @return : 삭제 성공 여부 
	 */
	public int deleteUser(Connection conn, UserDTO user) {
		PreparedStatement pstmt = null;
		
		String sql = """
				DELETE 
				FROM TB_USER
				WHERE USER_NAME=?
				AND 	USER_ID = ?
				AND 	USER_PW = ?
				""";
		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getUserPw());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(null, null, pstmt, conn);
		}
		return result;	
	}
	
	
	public UserDTO findUser(Connection conn, int userNo) {

    UserDTO memeber = null;
    String sql = """
            SELECT USER_NO, USER_ID, USER_PW, USER_NAME, ENROLL_DATE
            FROM TB_USER
            WHERE USER_NO = ?
            """;

    PreparedStatement pstmt = null;
    ResultSet rset = null;

    try {
        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, userNo);

        rset = pstmt.executeQuery();

        if (rset.next()) {
        	memeber = new UserDTO();
        	memeber.setUserNo(rset.getInt("USER_NO"));
        	memeber.setUserId(rset.getString("USER_ID"));
        	memeber.setUserPw(rset.getString("USER_PW"));
        	memeber.setUserName(rset.getString("USER_NAME"));
        	memeber.setEnrollDate(rset.getDate("ENROLL_DATE"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("DB 서버 오류! 오타가 나지 않았나요?");
    } finally {
        JdbcUtil.close(rset, null, pstmt, conn);
    }

    return memeber;
   }

	
	public UserDTO findUserId(Connection conn, String userId){
		
		String sql = """
				SELECT USER_NO, USER_ID, USER_PW, USER_NAME, ENROLL_DATE
				FROM TB_USER
				WHERE USER_ID = ?
				""";
		
		UserDTO memeber = null;
	  PreparedStatement pstmt = null;
    ResultSet rset = null;

    try {
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, userId);

        rset = pstmt.executeQuery();

        if (rset.next()) {
        	memeber = new UserDTO();
        	memeber.setUserNo(rset.getInt("USER_NO"));
        	memeber.setUserId(rset.getString("USER_ID"));
        	memeber.setUserPw(rset.getString("USER_PW"));
        	memeber.setUserName(rset.getString("USER_NAME"));
        	memeber.setEnrollDate(rset.getDate("ENROLL_DATE"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("DB 서버 오류! 오타가 나지 않았나요?");
    } finally {
        JdbcUtil.close(rset, null, pstmt, conn);
    }

    return memeber;
   }
	
}
