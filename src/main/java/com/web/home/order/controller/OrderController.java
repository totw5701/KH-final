package com.web.home.order.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.home.order.model.OrderDTO;
import com.web.home.order.service.OrderService;

@RestController
public class OrderController {
	

	@Inject
	OrderService orderService;
	
	@RequestMapping(value = "order", method = RequestMethod.POST)
	public String order(OrderDTO odto, HttpSession session, Model model, 
							HttpServletRequest request) {
		
		//바로구매시
		String flag = request.getParameter("flag"); //바로구매 여부 판별
		System.out.println("flag : " + flag);
		request.getParameter("pro_image2");
		request.getParameter("pro_title2");
		request.getParameter("pro_price2");
		request.getParameter("cart_cnt2");
		
		//총금액 계산		
		String totalamount = "0";
		
		
		if("y".equals(flag)){
			totalamount = request.getParameter("bamount2");
			System.out.println("테스트 : " + request.getParameter("bamount2"));
		}else{
			totalamount = request.getParameter("camount");
		}
		
		model.addAttribute("totalamount", totalamount);
		System.out.println("총금액 : " + totalamount);
		
		return "order/order";
	}
	//-----------------------------------------------------------------------------------------
	/*
	 * @RequestMapping(value="/cart", method=RequestMethod.GET) public ModelAndView
	 * list(@ModelAttribute CartVO vo, @ModelAttribute("cart") List<CartVO> cart,
	 * ModelAndView mav, Model model){
	 * 
	 * mav.setViewName("order/cart"); // view(jsp)의 이름 저장
	 * 
	 * if (!model.containsAttribute("cart")) { //빈 리스트가 cart라는 모델로
	 * 만들어지고 @SessionAttributes에 의해 //세션에 저장되고 JSP에서 ${cart}로 참조가능
	 * model.addAttribute("cart", new ArrayList<CartVO>()); }
	 * 
	 * logger.info("cart : " + cart.toString());
	 * 
	 * return mav; }
	 */
	//-----------------------------------------------------------------------------------------
	
	@RequestMapping(value="/payment2", method = RequestMethod.POST)
	public String OrderInsert(OrderDTO dto, HttpServletRequest request){	        

		//주문번호 입력
	    String orderSerial = request.getParameter("merchant_uid");	    
	    dto.setOrder_serial(orderSerial);
	    
		orderService.Insert(dto);
		
		
		return "/order/result";	
	    }

}
