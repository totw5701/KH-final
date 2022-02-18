package com.web.home.product.controller;


import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.home.book.model.*;
import com.web.home.book.model.dto.*;
import com.web.home.book.service.BookService;

import com.web.home.book.model.*;
import com.web.home.book.service.BookService;
import com.web.home.order.model.CartVO;

@SessionAttributes({"cart"})
@RestController
public class ProductController {
	

	
	@Autowired
	BookService bookservice;

	//1. 상품 전체 목록	
	@RequestMapping(value="/all")
	public @ResponseBody List<Map<String, Object>> list(Model model ) {
		//mav.setViewName("product");
		List<Map<String, Object>>  list = new ArrayList<Map<String, Object>> ();
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", bookservice.listProduct());
	  	if (!model.containsAttribute("cart")) {
	 		//빈 리스트가 cart라는 모델로 만들어지고  @SessionAttributes에 의해
	 		//세션에 저장되고 JSP에서 ${cart}로 참조가능
	 	   map.put("cart", new ArrayList<CartVO>());
	 	} 
	  	list.add(map);
		
		return list;
	}
		
	
    //2. 상품 상세보기
    @RequestMapping(value="/productDetail/{pro_num}", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> detail(@PathVariable("pro_num") int pro_num,Model model){
      //  mav.setViewName("productDetail");
    	Map<String, Object> map = new HashMap<String,Object>();
        map.put("vo", bookservice.detailProduct(pro_num));
        
 	   if (!model.containsAttribute("cart")) {
 			//빈 리스트가 cart라는 모델로 만들어지고  @SessionAttributes에 의해
 			//세션에 저장되고 JSP에서 ${cart}로 참조가능
 		    map.put("cart", new ArrayList<CartVO>());
 	   } 	    
        return map;
    }

    
    //3.1. 장바구니 이동
	@RequestMapping(value="/cart", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> list(@ModelAttribute CartVO vo, @ModelAttribute("cart") List<CartVO> cart, Model model){		
			Map<String, Object> map = new HashMap<String,Object>();
		// mav.setViewName("order/cart");    // view(jsp)의 이름 저장

	        if (!model.containsAttribute("cart")) {
	 			//빈 리스트가 cart라는 모델로 만들어지고  @SessionAttributes에 의해
	 			//세션에 저장되고 JSP에서 ${cart}로 참조가능
	 		    map.put("cart", new ArrayList<CartVO>());
	 	   } 

        	
	    return map;
	}


	
   //4. 장바구니 추가
   @RequestMapping(value="add", method = RequestMethod.POST)
   public List<CartVO> add(
		   	   //입력 파라미터인 pro_num, pro_price, pro_title, cart_cnt, pro_image가 vo에 일괄 바인딩 됨
		       //vo라는 이름의 Model로 저장(Request Scope)
		       @ModelAttribute CartVO vo,
		       //세션에서 cart라는 이름의 Model을 가져와 cart에 할당
		       @ModelAttribute("cart") List<CartVO> cart,
		       Model model) {	        

	   	 // cart라는 Model(ArrayList)에 사용자가 입력한 항목들을 가진 CartVO 객체를 Add
		 cart.add(vo);
		 
		 
		return cart;
   }

	
   
@RequestMapping(value="delete", method = RequestMethod.POST)
   public JSONObject delete(@ModelAttribute CartVO vo, @ModelAttribute("cart") List<CartVO> cart,
		   HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int ctnum = Integer.parseInt(request.getParameter("ctnum"));


		
		cart.remove(ctnum);	//cart.count가 1(index 0)일 경우 GET방식 전환 오류발생, 원인 확인 중...

	   json.put("url", "/order/cart");

	   return json;
   }


@RequestMapping(value="deleteAll", method = RequestMethod.POST)	//장바구니 전체 비우기
public JSONObject delete(@ModelAttribute("cart") List<CartVO> cart) {
		JSONObject json = new JSONObject();

	   cart.clear();
	   
	   json.put("url", "/order/cart");
	   
	   return json;
	}
}