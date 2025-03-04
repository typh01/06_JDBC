package com.kh.mvc.run;

import com.kh.mvc.view.UserView;

public class Run {
	
	public static void main(String[] args) {
		
			//EntryPoint - JVM이 프로그램 실행 시켜줌
		/*
		 * Model : 데이터 관련 모든 작업 (비즈니스 로직, 영속성 저장 등..)
		 * View : 화면 입/출력 (프레젠테이션 로직)
		 * Controller : View에서의 요청을 받아서 처리해주는 역할
		 */
		
//	HashMap / Hashtable / TreeMap / Properties ...
		
//		Properties prop = new Properties();
//		prop.put(String, String);
		
		// 결합도
		// 상속은 결합도 때문에 문제가 됨
		
		// composition (합성)
		// ex. HashMap의 값을 넣을 때 String, String으로 받고 싶다
		//	+ 필드로 받아서 put 함수를 수정
		// - 기본적으로 상속보단 합성을 사용하자.
		
		new UserView().mainMenu();
		
	}
	
}
