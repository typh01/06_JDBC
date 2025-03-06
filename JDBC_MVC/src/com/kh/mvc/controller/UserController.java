package com.kh.mvc.controller;

import java.util.List;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.MemberService;

/**
 * VIEW 에서 온 요청을 처리해주는 클래스입니다.
 * 메소드로 전달된 데이터값을 가공처리한 후 DAO로 전달합니다.
 * DAO로부터 반환받은 결과를 사용자가 보게될 View(응답화면)에 반환합니다.
 */
public class UserController {

		private MemberService userService = new MemberService();
	
		public List<UserDTO>  findAll() {
			return userService.findAll();
		}
		
		
		public int insertUser(String userId, String userPw, String userName) {
			UserDTO user = new UserDTO();
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setUserName(userName);
			
			int result = userService.insertUser(user);
			user = null;
			return result;
		}

		
		public int modifyPw(String userId, String userPw, String newPw) {
			UserDTO user = new UserDTO();
			user.setUserId(userId);
			user.setUserPw(userPw);
			
			int result = userService.modifyPw(user, newPw);
			user = null;
			return result;
		}

		
		public int deleteUser(String userId, String userPw, String userName) {
			UserDTO user = new UserDTO();
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setUserName(userName);
			
			int result = userService.deleteUser(user);
			user = null;
			return result;
		}
		
		
		public UserDTO findUser(int userNo) {
			UserDTO user = new UserDTO();
			user.setUserNo(userNo);
			
			user = null;
			return userService.findUser(userNo);
		}
		
		
		public UserDTO findUserId(String userId) {
			UserDTO user = new UserDTO();
			user.setUserId(userId);
			
			user = null;
			return userService.findUserId(userId);
		}
		
	
}
