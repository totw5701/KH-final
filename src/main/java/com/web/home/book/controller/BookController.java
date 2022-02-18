package com.web.home.book.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.home.book.model.*;
import com.web.home.book.model.dto.*;
import com.web.home.book.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping({"/products/{pageNum}", "/products"})
    public ResultResponseDto pageList(@PathVariable(value = "pageNum", required = false) Integer num) {
        if(num == null) num = 0;
        List<BookDTO> products = bookService.getProducts(num);
        Integer count = bookService.getProductsPageCount();
        PagingDto pagingDto = new PagingDto(count, num);
        return new ResultResponseDto(pagingDto, products);
    }

    @PostMapping({"/products/{pageNum}", "/products"})
    public ResultResponseDto pageList(@PathVariable(value = "pageNum", required = false) Integer num,
                                  @RequestParam("keyword") String keyword,
                                  @RequestParam("type") PRODUCT_SEARCH_TYPE type) {
        if(num == null) num = 0;

        List<BookDTO> products = bookService.search(num, type, keyword);
        Integer count = bookService.searchPageCount(type, keyword);
        PagingDto pagingDto = new PagingDto(count, num);
        return new ResultResponseDto(pagingDto, products);
    }

    @GetMapping({"/products/bestseller/{pageNum}", "/products/bestseller"})
    public ResultResponseDto bestSeller(@PathVariable(value = "pageNum", required = false) Integer num) {
        if(num == null) num = 0;

        List<BookDTO> products = bookService.orderSales(num);
        Integer count = bookService.getProductsPageCount();
        PagingDto pagingDto = new PagingDto(count, num);
        return new ResultResponseDto(pagingDto, products);
    }

    @GetMapping({"/products/new-book/{pageNum}", "/products/new-book"})
    public ResultResponseDto newBook(@PathVariable(value = "pageNum", required = false) Integer num,
                                 @RequestParam(value = "date" , required = false) LocalDateTime date) {
        if(num == null) num = 0;
        if(date == null) LocalDateTime.now().minusYears(1);
        List<BookDTO> products = bookService.orderRecent(num, date);
        Integer count = bookService.orderRecentPageCount(date);
        PagingDto pagingDto = new PagingDto(count, num);
        return new ResultResponseDto(pagingDto, products);
    }

    @GetMapping({"/products/domestic/{pageNum}", "/products/domestic"})
    public ResultResponseDto domesticBook(@PathVariable(value = "pageNum", required = false) Integer num) {
        if(num == null) num = 0;
        List<BookDTO> products = bookService.domesticList(num);
        Integer count = bookService.domesticListPageCount();
        PagingDto pagingDto = new PagingDto(count, num);
        return new ResultResponseDto(pagingDto, products);
    }

    @GetMapping({"/products/abroad/{pageNum}", "/products/abroad"})
    public ResultResponseDto abroadBook(@PathVariable(value = "pageNum", required = false) Integer num) {
        if(num == null) num = 0;
        List<BookDTO> products = bookService.abroadList(num);
        Integer count = bookService.abroadListPageCount();
        PagingDto pagingDto = new PagingDto(count, num);
        return new ResultResponseDto(pagingDto, products);
    }
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public Model exam(HttpServletRequest request,Model model, @RequestBody HashMap<String, Object> map) throws IOException, ParseException {
		request.setCharacterEncoding("UTF-8");
		
		
		BookDTO dto = new BookDTO();
		MultipartRequest multi = new MultipartRequest(
				request,
				request.getServletContext().getRealPath("resources/images"),
				1024*1024*10,//업로드 파일 크기 제한
				"utf-8",
				new DefaultFileRenamePolicy());	//중복 파일 업로드시 이름변경
		
		String file_addres = "resources/images/" + multi.getFilesystemName("uploadfile");
		dto.setPro_title(multi.getParameter("pro_title"));
		dto.setWriter(multi.getParameter("pro_writer"));
		dto.setPro_publisher(multi.getParameter("pro_publisher"));
		dto.setPro_contry(multi.getParameter("pro_country"));
		dto.setPro_contents(multi.getParameter("pro_contents"));
		dto.setPrice(Integer.parseInt(multi.getParameter("pro_price")));
		String date = multi.getParameter("year") 
				  	+ multi.getParameter("month")
					+ multi.getParameter("day");
		dto.setPro_date(date);
		dto.setPro_image(file_addres);
		if(bookService.insertbook(dto)) {
			model.addAttribute("msg","상품을 등록하였습니다.");
			model.addAttribute("url","/home");
		}else {
			model.addAttribute("msg","상품등록이 실패하였습니다.");
			model.addAttribute("url","/home");
		}
		return model;
	}
}
