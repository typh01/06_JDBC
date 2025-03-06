package com.kh.mvc2.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc2.controller.EmployeeController;
import com.kh.mvc2.model.dto.EmployeeDTO;

public class EmployeeView {
	
	private Scanner sc = new Scanner(System.in);
	private EmployeeController emController = new EmployeeController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("===========================================");
			System.out.println("1. Employee 에서 SELECT 하기~");
			System.out.println("2. Employee에 값 INSERT 하기!");
			System.out.println("3. Employee 에서 UPDATE 하기!");
			System.out.println("4. Employee의 값 DELETE 하기!");
			System.out.println("프로그램 종료하기!");
			System.out.print("이용할 번호를 입력해주세요~~~");
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
				EmSelect();
				break;
			case 2 : 
				EmInsert();
				break;
			case 3 :
				EmUpdate();
				break;
			case 4:
				break;
			case 9 : 
				System.out.println("프로그램 종료합니당 ~ "); 
				return;
			default : 
				System.out.println("잘못된 입력! 제대로 입력하세요!");
				}
			}
		}


	public void EmSelect() {
		System.out.println("\n - - - 사원 전체 목록입니다 - - -");
		List<EmployeeDTO> list = emController.EmSelect();
		
		System.out.println("\n 조회된 총 회원의 수는 " + list.size() + "명 입니다.");
		if(!(list.isEmpty())) {
			System.out.println("====================================================");
			for(EmployeeDTO employee : list) {
				System.out.print(employee.getEmpName() + " 사원의 정보 !");
				System.out.print("\n사번 : " + employee.getEmpId());
				System.out.print(" 입사일 : " + employee.getHireDate()+ "\n");
				System.out.println();
			}
			
			System.out.println("====================================================");
					
		} else {
			System.out.println("조회된 회원이 존재하지 않습니다.");
		}
		
	}
	
	
	private void EmInsert() {
		System.out.println("=== 사원 등록! ===");
		
		System.out.print("이름 입력 > ");
		String emName = sc.nextLine();
		System.out.print("주민번호 입력 > ");
		String emNo = sc.nextLine();
		System.out.print("이메일 입력 > ");
		String phone = sc.nextLine();
		
		int result = emController.EmInsert();
		
		if(result > 0) {
			System.out.println( emName + " 입사를 축하드립니다!");
		}else {
			System.out.println("등록 실패 내용을 확인해주세요~");
		}
		
	}
	
	
	private void EmUpdate() {
		
		
	}
	
}

