package com.kh.mvc.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;

/**
 * MemberView 클래스는 JDBC 실습을 위해 생성하였으며,
 * 사용자에게 입력 및 출력을 수행하는 메소드를 제공합니다.
 * 
 * @author : 종로 C강의장
 * @version : 0.1
 * @date : 2025-03-04
 */
public class UserView {
	
	private Scanner sc = new Scanner(System.in);
	private UserController userController = new UserController();
	
	/**
	 * 프로그램 시작 시 사용자에게 보여줄 메인화면을 출력해주는 메소드입니다.
	 */
	public void mainMenu() {
		
		while(true) {
			System.out.println("- - - USER 테이블 관리 프로그램 - - -");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가"); // 한 행 추가
			System.out.println("3. 비밀번호 수정");
			System.out.println("4. 회원 탈퇴");
			System.out.println("5. 회원 번호로 검색 ");
			System.out.println("6. 회원 아이디로 검색");
			System.out.println("9. 프로그램 종료");
			System.out.println("이용할 메뉴를 선택해주세요 > ");
			int menuNo = 0;
			
			try {
				menuNo = sc.nextInt();
			} catch(InputMismatchException e){
				sc.nextLine();
				continue;
			}
			
			sc.nextLine();
			
			switch (menuNo) {
			case 1 : 
				findAll();
				break;
			case 2 : 
				insertUser();
				break;
			case 3 :
				modifyPw();
				break;
			case 4:
				deleteUser();
				break;
			case 5:
				findUserNo();
				break;
			case 6:
				findUserId();
				break;
			case 9 : 
				System.out.println("프로그램 종료 ~ "); 
				return;
			default : 
				System.out.println("잘못된 메뉴 선택입니다.");
			}
		
		}
		
		
	}


		// 회원 전체 정보를 조회해주는 기능
		private void findAll() {
			
			System.out.println("\n - - - 회원 전체 목록입니다 - - -");
			// Controller에 DB 테이블에 있는 회원 전체 목록 요청
			List<UserDTO> list = userController.findAll();
			
			System.out.println("\n 조회된 총 회원의 수는 " + list.size() + "명 입니다.");
			if(!(list.isEmpty())) {
				
				System.out.println("====================================================");
				for(UserDTO user : list) {
					System.out.print(user.getUserName() + "님의 정보 !");
					System.out.print("\n아이디 : " + user.getUserId());
					System.out.print(" 가입일 : " + user.getEnrollDate() + "\n");
					System.out.println();
				}
				
				System.out.println("====================================================");
						
			} else {
				System.out.println("조회된 회원이 존재하지 않습니다.");
			}
			
		}
	
		/**
		 * TB_USER에 INSERT 할 값을 사용자에게 입력받도록 유도하는 화면
		 */
		private void insertUser() {
			
			System.out.println("- - - 회원 추가 - - -");
			
			System.out.print("아이디를 입력하세요 > ");
			String userId = sc.nextLine();
			// UNIQUE 라고 가정하에 입력받은 아이디 가지고 DB가서 WHERE 조건절에다가 사용자가 입력한 아이디 넣어서
			// 조회 결과와 비교하기
			/*
			while(true) {
				SELECT USER)ID FROM TB_USER WHERE USER_ID = 사용자가 입력한 아이디값
				break;
			}
			System.out.println("중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요!");
			*/
			// userId.matches("^[a-zA-Z]")
			/*
			if(userId.length() > 30) {
				System.out.println("아이디는 30자 이내로 입력해주세요.");
			}
			*/
			System.out.print("비밀번호를 입력하세요 > ");
			String userPw = sc.nextLine();
			System.out.print("이름을 입력하세요 > ");
			String userName = sc.nextLine();
			
			int result =  userController.insertUser(userId, userPw, userName);
			
			if(result > 0) {
				System.out.print(userName + "님 가입에 성공하셨습니다. \n");
			} else {
				System.out.print("회원 추가에 실패했습니다. 다시 시도해주세요 ~ \n");
			}
		}
	
		// 회원의 비밀번호를 수정하는 기능
		private void modifyPw() {
			System.out.print("아이디를 입력하세요 > ");
			String userId = sc.nextLine();
			System.out.print("비밀번호를 입력하세요 > ");
			String userPw = sc.nextLine();
			System.out.print("수정할 비밀번호를 입력하세요 > ");
			String newPw = sc.nextLine();
			
			int result = userController.modifyPw(userId, userPw, newPw);
			
			if(result > 0) {
				System.out.print("비밀번호 수정에 성공하셨습니다. \n");
			} else {
				System.out.print("비밀번호 수정에 실패했습니다! 아이디와 비밀번호를 확인해주세요 ~ ");
			}
			
		}
		
		// 회원 탈퇴하는 기능
		private void deleteUser() {
			System.out.print("이름을 입력하세요 > ");
			String userName = sc.nextLine();
			System.out.print("아이디를 입력하세요 > ");
			String userId = sc.nextLine();
			System.out.print("비밀번호를 입력하세요 > ");
			String userPw = sc.nextLine();
			
			int result =  userController.deleteUser(userId, userPw, userName);
			
			if(result > 0) {
				System.out.print(userName + "님 탈퇴에 성공하셨습니다. \n");
			} else {
				System.out.print("탈퇴 실패... 정보를 다시 확인해주세요 ~ \n");
			}
		}
	
		//회원 번호로 찾기
		private void findUserNo() {
	   System.out.print("회원번호를 입력하세요 > ");
	   int userNo = sc.nextInt();
	   
	   UserDTO member = userController.findUser(userNo);
	   
	   if (member != null) { // null 체크
	       System.out.println("------------------------------------");
	       System.out.print("회원 번호 : " + member.getUserNo());
	       System.out.println("\n" + member.getUserName() + "님의 정보 !");
	       System.out.println(" 아이디 : " + member.getUserId());
	       System.out.print(" 가입일 : " + member.getEnrollDate() + "\n");
	       System.out.println("------------------------------------");
	   } else {
	       System.out.println("입력하신 번호의 회원이 존재하지 않습니다.");
	   }
	  }
		
		
		// 아이디로 찾기
		private void findUserId() {
			System.out.print("아이디를 입력하세요 > ");
			String userId = sc.nextLine();
			
			UserDTO member = userController.findUserId(userId);
			
			if(member != null) {
					System.out.println("------------------------------------");
					System.out.print("아이디 : " + member.getUserId());
					System.out.println("\n" + member.getUserName() + "님의 정보 !");
					System.out.println(" 회원 번호 : " + member.getUserNo());
					System.out.print(" 가입일 : " + member.getEnrollDate() + "\n");
					System.out.println("------------------------------------");
				} else {
				System.out.println("입력하신 번호의 회원이 존재하지 않습니다.");
			}
		}
	
}

