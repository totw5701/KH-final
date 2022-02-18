package com.web.home.account.model;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AccountDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountDAO.class);

	
	@Autowired
	private SqlSession sess;
	
	public AccountDTO selectAccount(AccountDTO dto) {
		return this.sess.selectOne("AccountMapper.selectAccount",dto);
	}
	
	public boolean joinAccount(AccountDTO dto) {
		int res = this.sess.insert("AccountMapper.insertAccount", dto);
		return res == 1 ? true : false;
	}
	public AccountDTO findAccount(AccountDTO dto) {
		return this.sess.selectOne("AccountMapper.findAccount",dto);
	}
	
	public AccountDTO mypageAccount(String username) {
		return this.sess.selectOne("AccountMapper.mypageAccount", username);
	}
	public boolean deleteAccount(String username) {
		int res = this.sess.delete("AccountMapper.deleteAccount", username);
		return res == 1 ? true : false;
	}
	public boolean updateAccount(AccountDTO dto) {
		int res = this.sess.update("AccountMapper.updateAccount", dto);
		return res == 1 ? true : false;
	}
	public boolean updateAuthKey(AccountDTO dto) {
		int res = this.sess.update("AccountMapper.updateAuthKey", dto);
		return res == 1 ? true : false;
	}
	public boolean signupconfirm(String email, String authkey) {
		AccountDTO dto = new AccountDTO();
		dto.setUser_authkey(authkey);
		dto.setUser_email(email);
		int res = this.sess.update("AccountMapper.signupconfirm", dto);
		return res == 1 ? true : false;
	}
	public AccountDTO findPwd(String userid, String email) {
		AccountDTO dto = new AccountDTO();
		dto.setUser_id(userid);
		dto.setUser_email(email);

		return this.sess.selectOne("AccountMapper.findPwd", dto);
	}
}
