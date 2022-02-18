package com.web.home.order.model;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OrderDAO {
	

	
	@Autowired
	private SqlSession sess;
	
	public List<OrderDTO> ordercheck (String username){
		List<OrderDTO> data = this.sess.selectList("OrderMapper.ordercheck",username);

		return data;
	}
	public boolean Insert(OrderDTO dto){
		int res = this.sess.selectOne("OrderMapper.InsertOrder", dto);
		
		
		return res == 1 ? true : false;
	}
}

