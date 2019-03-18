package org.nz.controller.front;

import java.util.List;


import org.nz.bean.Book;
import org.nz.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月28日 下午3:25:58
* 类说明：
*
*/
@Controller
@RequestMapping("/front/book")
public class BookController{
	
	@Autowired
	private BookService bookService;
	/**
	 * @param cid  分类id
	 * @param model 
	 * @param pn  页码
	 * @return
	 */
	@RequestMapping("/list/{field}/{pn}")
	public String list(@PathVariable("field") String field,Model model,@PathVariable("pn") int pn) {
		System.out.println(field);
		model.addAttribute("field", field); 
		System.out.println(pn);
		PageHelper.startPage(pn,12);
		List<Book> books = bookService.findBookByCategoryId(field);
		PageInfo<Book> pages = new PageInfo<>(books,5);
		model.addAttribute("pages", pages);
		
		System.out.println(pages);
		return "front/jsps/book/list";
	}
	/**
	 * 图书详细
	 * @param bid 图书id
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail/{bid}")
	public String detail(@PathVariable("bid") String bid,Model model) {
		System.out.println(bid);
		Book book = bookService.findBookById(bid);
		model.addAttribute("book", book);
		return "front/jsps/book/desc";
	}
	
}
