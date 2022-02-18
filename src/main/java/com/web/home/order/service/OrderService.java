package com.web.home.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.home.order.model.OrderDAO;
import com.web.home.order.model.OrderDTO;

@Service
public class OrderService {
	
	@Autowired
	private OrderDAO dao;
	
	public List<OrderDTO> ordercheck (String username){
		List<OrderDTO> data = dao.ordercheck(username);
		return data;
	}
	public boolean Insert(OrderDTO dto) {
		
		return dao.Insert(dto);
	}
	
}
