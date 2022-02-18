package com.web.home.book.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.home.book.model.BookDAO;
import com.web.home.book.model.BookDTO;
import com.web.home.book.model.dto.FilterInfo;
import com.web.home.book.model.dto.OrderInfo;
import com.web.home.book.model.dto.*;
import com.web.home.book.model.dto.PagingInfo;
import com.web.home.book.model.dto.RecentInfo;
import com.web.home.book.model.dto.SearchInfo;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;
    
	public boolean insertbook(BookDTO dto) {
		return bookDAO.insertbook(dto);
	}
    
	// 01. 상품목록
	public List<BookDTO> listProduct(){
		return bookDAO.listProduct();
	}
	
	// 02. 상품상세
    public BookDTO detailProduct(int pro_num) {
        return bookDAO.detailProduct(pro_num);
    }
	
    public List<BookDTO> getProducts(int page) {
        PagingInfo pagingInfo = new PagingInfo(page);
        return bookDAO.selectProductsPaging(pagingInfo);
    }

    public Integer getProductsPageCount() {
        return bookDAO.selectProductsPageCount();
    }

    public List<BookDTO> search(int page, PRODUCT_SEARCH_TYPE type, String keyword) {
        SearchInfo searchInfo = new SearchInfo(page, type, keyword);
        return bookDAO.selectProductsSearch(searchInfo);
    }

    public Integer searchPageCount(PRODUCT_SEARCH_TYPE type, String keyword) {
        SearchInfo searchInfo = new SearchInfo(0, type, keyword);
        return bookDAO.selectProductsSearchPageCount(searchInfo);
    }

    public List<BookDTO> orderRecent(int page, LocalDateTime date) {
        System.out.println(date);
        System.out.println(date);
        System.out.println(date);
        System.out.println(date);
        System.out.println(date);
        System.out.println(date);
        System.out.println(date);
    	RecentInfo recentInfo = new RecentInfo(page, date);
        return bookDAO.selectProductsRecent(recentInfo);
    }

    public Integer orderRecentPageCount(LocalDateTime date) {
        RecentInfo recentInfo = new RecentInfo(0, date);
        return bookDAO.selectProductsRecentPageCount(recentInfo);
    }

    public List<BookDTO> orderSales(int page) {
        OrderInfo orderInfo = new OrderInfo(page, PRODUCT_ORDER_TYPE.BEST_SELLER);
        return bookDAO.selectProductsOrderd(orderInfo);
    }

    public List<BookDTO> domesticList(int page) {
    	
        FilterInfo filterInfo = new FilterInfo(page, PRODUCT_FILTER_TYPE.COUNTRY, PRODUCT_COUNTRY.KOR.toString());
        return bookDAO.selectProductsFiltered(filterInfo);
    }

    public Integer domesticListPageCount() {
        FilterInfo filterInfo = new FilterInfo(0, PRODUCT_FILTER_TYPE.COUNTRY, PRODUCT_COUNTRY.KOR.toString());
        return bookDAO.selectProductsFilteredPageCount(filterInfo);
    }

    public List<BookDTO> abroadList(int page) {
        FilterInfo filterInfo = new FilterInfo(page, PRODUCT_FILTER_TYPE.COUNTRY, PRODUCT_COUNTRY.KOR.toString());
        return bookDAO.selectProductsNotFiltered(filterInfo);
    }

    public Integer abroadListPageCount() {
        FilterInfo filterInfo = new FilterInfo(0, PRODUCT_FILTER_TYPE.COUNTRY, PRODUCT_COUNTRY.KOR.toString());
        return bookDAO.selectProductsNotFilteredPageCount(filterInfo);
    }


}