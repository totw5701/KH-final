package com.web.home.account.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.home.account.model.AccountDAO;
import com.web.home.account.model.AccountDTO;

@Service
public class AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountDAO dao;
	
	public boolean login(String username, String password) {

		AccountDTO dto = new AccountDTO(username, password);
		AccountDTO data = dao.selectAccount(dto);
		
		if(data != null) {
			
			return true;
		}
		
		return false;
	}
	
	public AccountDTO mypage(String username) {
		AccountDTO data = dao.mypageAccount(username);
		if(data != null) {
			return data;
		}
		
		return null;
	}
	
	public boolean join(AccountDTO dto) {
		return dao.joinAccount(dto);
	}
	
	public boolean check(String userid) {
		AccountDTO dto = new AccountDTO();
		dto.setUser_id(userid);
		if(dao.findAccount(dto) == null) {
			return true;
		}else {return false;}
	}
	
	public boolean delete(String username) {
		return dao.deleteAccount(username);
	}
	public boolean update(AccountDTO dto) {
		return dao.updateAccount(dto);
	}
	
	public boolean updateAuthKey(AccountDTO dto) {
		return dao.updateAuthKey(dto);
	}
	
	public boolean signupconfirm(String email, String authkey)
	{
		return dao.signupconfirm(email,authkey);
	}
	public AccountDTO findPwd(String username, String email)
	{
		return dao.findPwd(username, email);

	}
}
