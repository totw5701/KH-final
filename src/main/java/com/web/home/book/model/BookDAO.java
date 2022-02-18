package com.web.home.book.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.home.advice.exception.CDBException;
import com.web.home.book.model.dto.FilterInfo;
import com.web.home.book.model.dto.OrderInfo;
import com.web.home.book.model.dto.PagingInfo;
import com.web.home.book.model.dto.RecentInfo;
import com.web.home.book.model.dto.SearchInfo;


@Repository
public class BookDAO {
	@Autowired
	private SqlSession sess;
	
	
	
	public boolean insertbook(BookDTO dto) {
		int res = this.sess.insert("BookMapper.insertBook",dto);
		return res == 1 ? true : false;
	}

	public List<BookDTO> selectProductsPaging(PagingInfo pagingInfo) {
        System.out.println(pagingInfo.getFrom());
        System.out.println(pagingInfo.getFrom());
        System.out.println(pagingInfo.getFrom());
        List<Object> list;
        list = this.sess.selectList("BookMapper.selectPagingProducts", pagingInfo);


        ArrayList<BookDTO> proList = new ArrayList<BookDTO>();
        for (Object o : list) {
            proList.add((BookDTO) o);
        }

        return proList;
    }
	

	
	public List<BookDTO> listProduct() {
		List<BookDTO> data = this.sess.selectList("BookMapper.SelectProduct");		
		return data;
	}
	
    public BookDTO detailProduct(int pro_num) {
        return sess.selectOne("BookMapper.detailProduct", pro_num);
    }
    
    public int selectProductsPageCount() {
    	

        int num;
        try {
            num = this.sess.selectOne("BookMapper.selectPageCountProducts");
    	} catch (Exception e){
            throw new CDBException();
        }
        return num;
    }

    // 검색
    public List<BookDTO> selectProductsSearch(SearchInfo searchInfo) {

        List<Object> list;
        try {
            list = this.sess.selectList("BookMapper.selectProductsSearch", searchInfo);
        } catch (Exception e){
            throw new CDBException();
        }

        ArrayList<BookDTO> proList = new ArrayList<BookDTO>();
        for (Object o : list) {
            proList.add((BookDTO) o);
        }

        return proList;
    }

    public int selectProductsSearchPageCount(SearchInfo searchInfo) {

        int num;
        try {
            num = this.sess.selectOne("BookMapper.selectPageCountProductsSearch", searchInfo);
        } catch (Exception e){
            throw new CDBException();
        }

        return num;
    }

    // 정렬
    public List<BookDTO> selectProductsOrderd(OrderInfo orderInfo) {

        List<Object> list;

            list = this.sess.selectList("BookMapper.selectProductsOrdered", orderInfo);
       
        ArrayList<BookDTO> proList = new ArrayList<BookDTO>();
        for (Object o : list) {
            proList.add((BookDTO) o);
        }

        return proList;
    }

    // 최근 상품 검색
    public List<BookDTO> selectProductsRecent(RecentInfo recentInfo) {
        List<Object> list;
       
            list = this.sess.selectList("BookMapper.selectProductsRecentProduct", recentInfo);
        

        ArrayList<BookDTO> proList = new ArrayList<BookDTO>();
        for (Object o : list) {
            proList.add((BookDTO) o);
        }

        return proList;
    }

    public int selectProductsRecentPageCount(RecentInfo recentInfo) {
        int num;
            num = this.sess.selectOne("BookMapper.selectPageCountProductsRecentProduct", recentInfo);
       

        return num;
    }

    // 필터링
    public List<BookDTO> selectProductsFiltered(FilterInfo filterInfo) {

        List<Object> list;

            list = this.sess.selectList("BookMapper.selectProductsFiltered", filterInfo);


        ArrayList<BookDTO> proList = new ArrayList<BookDTO>();
        for (Object o : list) {
            proList.add((BookDTO) o);
        }

        return proList;
    }

    public List<BookDTO> selectProductsNotFiltered(FilterInfo filterInfo) {

        List<Object> list;

            list = this.sess.selectList("BookMapper.selectProductsNotFiltered", filterInfo);


        ArrayList<BookDTO> proList = new ArrayList<BookDTO>();
        for (Object o : list) {
            proList.add((BookDTO) o);
        }

        return proList;
    }

    public int selectProductsFilteredPageCount(FilterInfo filterInfo) {

        int num;

            num = this.sess.selectOne("BookMapper.selectPageCountProductsFiltered", filterInfo);


        return num;
    }

    public int selectProductsNotFilteredPageCount(FilterInfo filterInfo) {

        int num;

            num = this.sess.selectOne("BookMapper.selectPageCountProductsNotFiltered", filterInfo);


        return num;
    }
}
