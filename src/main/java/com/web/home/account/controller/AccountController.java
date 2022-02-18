package com.web.home.account.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.home.account.model.*;
import com.web.home.account.service.AccountService;
import com.web.home.account.service.MailSendService;
import com.web.home.order.model.OrderDTO;
import com.web.home.order.service.OrderService;


@Controller
@RequestMapping("")
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	

	@Autowired
	private OrderService orderservice;
    @Autowired
    private MailSendService mss; 
	@Autowired
	private AccountService service;
    

	@RequestMapping("/join")
	public @ResponseBody JSONObject join(@RequestBody HashMap<String, Object> param, HttpServletRequest request, Model model) {
		
		JSONObject json = new JSONObject();
		
		String userid = (String) param.get("userid");
		String username = (String) param.get("username");
		String password = (String) param.get("password");
		String email = (String) param.get("email");
		String address = (String) param.get("address");
		String phone = (String) param.get("phone");
		
		AccountDTO dto= new AccountDTO();
		dto.setUser_id(userid);
		dto.setUser_name(username);
		dto.setUser_pwd(password);
		dto.setUser_email(email);
		dto.setUser_address(address);
		dto.setUser_phone(phone);
		
		

		Map<String, String> map = new HashMap<String, String>();
		if(service.join(dto)) {
			//임의의 authKey 생성 & 이메일 발송
	        String authKey = mss.sendAuthMail(dto.getUser_email());
	        dto.setUser_authkey(authKey);

	        
	        map.put("email", dto.getUser_email());
	        map.put("authKey", dto.getUser_authkey());
	        System.out.println(map);

	        json.put("userid", userid);
			json.put("username", username);
			json.put("password", password);
			json.put("email", email);
			json.put("address", address);
			json.put("phone", phone);

			//DB에 authKey 업데이트
	        service.updateAuthKey(dto);
			model.addAttribute("join_message","가입에성공하였습니다.");
			return json;
		}else {
			model.addAttribute("join_message","가입에실패하였습니다.");
			return json;
		}
	}
	@RequestMapping(value="/join/check", method=RequestMethod.GET)
	public void joincheck(String username,HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		
		if(service.check(username)) {
			json.put("state", "success");
			json.put("msg", "사용 가능한 아이디 입니다.");
		} else {
			json.put("state", "fail");
			json.put("msg", "사용 불가능한 아이디 입니다.");
		}
		response.getWriter().println(json.toJSONString());
		response.getWriter().flush();
	}
	

	@RequestMapping(value="/login", method=RequestMethod.POST)
	   public Map<String,Boolean> login(String username, String password, Model model, HttpServletRequest request) {
	      HttpSession session = request.getSession();
	      HashMap<String,Boolean> map = new HashMap<String, Boolean>();
	      boolean result = service.login(username,password);
	      
	      if(result) {
	         session.setAttribute("username", username);
	         map.put("logined", true);
	          return map;
	      }
	      map.put("logined", false);
	      return map;
	   }
	
	@GetMapping("/mypage")
	public @ResponseBody List<Map<String, Object>>  mypage( HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();
		List<Map<String, Object>>  list = new ArrayList<Map<String, Object>> ();
		Map<String, Object> map = new HashMap<String,Object>();
		
		if(session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
		   	AccountDTO data = service.mypage(username);

			if(data!=null) {
			List<OrderDTO> orderdata = orderservice.ordercheck(username);
			map.put("orderdata", orderdata);
			map.put("mydata", data);
			list.add(map);
		    return list;
		}}		
		return list;
}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout( HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
	@RequestMapping(value="/deleteuser", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> deleteuser( HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String,Object>();
		String username = (String) session.getAttribute("username");
		if(service.delete(username)) {
			map.put("msg","회원탈퇴에 성공하였습니다.");
			map.put("url","/");
			session.invalidate();
		}else {
			map.put("msg","회원탈퇴에 실패했습니다.");
			map.put("url","/");
		}
		return map;
	}
	@RequestMapping(value="/updateuser", method=RequestMethod.POST)
	public @ResponseBody JSONObject updateuser( @RequestBody HashMap<String, Object> param,HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		JSONObject json = new JSONObject();

		
		String password = (String) param.get("password");
		String email = (String) param.get("email");
		String address = (String) param.get("address");
		String phone = (String) param.get("phone");
		AccountDTO dto = new AccountDTO();
		dto.setUser_address(address);
		dto.setUser_pwd(password);
		dto.setUser_email(email);
		dto.setUser_phone(phone);
		dto.setUser_id(userid);

		
		if(service.update(dto)) {
			json.put("msg","회원수정에 성공하였습니다.");
			json.put("url","/");
		}else {
			json.put("msg","회원수정에 실패했습니다.");
			json.put("url","/");
		}
		return json;
	}
	@RequestMapping(value="/updateuser", method=RequestMethod.GET)
	public @ResponseBody AccountDTO updateuser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountDTO data = new AccountDTO();

		
		if(session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
		   	data = service.mypage(username);
		   	return data;
		}		
		
		return data;
	}
	@RequestMapping(value="/signUpConfirm", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> signupconfirm(HttpServletRequest request,String email, String authKey) {
		service.signupconfirm(email, authKey);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("msg","메일 인증이 완료되었습니다.");
		map.put("url","/");
		return map;
	}
	
	
	
	@RequestMapping(value="/findpwd", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> findPwd(@RequestBody HashMap<String, Object> param, Model model) {
		Map<String, Object> map = new HashMap<String,Object>();

		String userid = (String) param.get("userid");
		String email = (String) param.get("email");

		
		AccountDTO dto = service.findPwd(userid, email);
		if(dto != null) {
			mss.sendFindPwd(dto);
			map.put("msg","비밀번호를 이메일로 전송하였습니다.");
			map.put("url","/home");
		}else {
			map.put("msg","존재하지 않는 이메일이나 아이디 입니다.");
			map.put("url","/findpwd");
		}
		return map;	
	}
}
