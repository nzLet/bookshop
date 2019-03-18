package org.nz.controller.admin;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.nz.bean.Book;
import org.nz.bean.Category;
import org.nz.service.BookService;
import org.nz.service.CategoryService;
import org.nz.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
* @author 作者 : YN
* @version 创建时间：2019年2月14日 下午5:58:18
* 类说明：
*
*/
@Controller
@RequestMapping("/admin/book")
public class AdminBookController {

	
	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;
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
		return "admin/adminjsps/admin/book/list";
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
		if(!StringUtils.isEmpty(bid)) {
			Book book = bookService.findBookById(bid);
			List<Category> parents = categoryService.findAll();
			if(!ObjectUtils.isEmpty(book)&&parents.size()!=0) {
				model.addAttribute("parents", parents);
				model.addAttribute("book", book);
				return "admin/adminjsps/admin/book/desc";
			}
		}
		
		return "forward:/admin/book/list/5F79D0D246AD4216AC04E9C5FAB3199E/1";
	}
	@RequestMapping("/getChildren")
	@ResponseBody
	public List<Category> getChildren(String pid) {
		System.out.println(pid);
		List<Category> children = new ArrayList<Category>();
		if(!StringUtils.isEmpty(pid)) {
			children = categoryService.selectByPid(pid);
			
		}
		return children;
		
	}
	@RequestMapping("/update")
	public String update(Book book,Model model) {
		System.out.println(book);
		if(!ObjectUtils.isEmpty(book)&&!StringUtils.isEmpty(book.getBid())) {
			try {
				if(bookService.updateBook(book)) {
					model.addAttribute("msg", "修改成功！");
					return "forward:/admin/book/detail/"+book.getBid();
				}
			} catch (Exception e) {
				
			}
			model.addAttribute("msg", "修改失败！");
			return "forward:/admin/book/detail/"+book.getBid();
		}
		model.addAttribute("msg", "修改失败！");
		return "forward:/admin/book/list/5F79D0D246AD4216AC04E9C5FAB3199E/1";
	}
	@RequestMapping("/delete")
	public String delete(Book book,Model model) {
		if(!StringUtils.isEmpty(book.getBid())) {
			try {
				if(bookService.deleteBook(book.getBid())) {
					model.addAttribute("msg", "删除成功！");
					return "forward:/admin/book/list/5F79D0D246AD4216AC04E9C5FAB3199E/1";
				}
			} catch (Exception e) {
				
			}
			model.addAttribute("msg", "删除失败！");
			return "forward:/admin/book/detail/"+book.getBid();
		}
		model.addAttribute("msg", "删除失败！");
		return "forward:/admin/book/list/5F79D0D246AD4216AC04E9C5FAB3199E/1";
	}
	@RequestMapping("/goAdd")
	public String goAdd(Model model) {
		List<Category> parents = categoryService.findAll();
		if(parents.size()!=0) {
			
			model.addAttribute("parents", parents);
			return "admin/adminjsps/admin/book/add";
		}
		return "forward:/admin/book/list/5F79D0D246AD4216AC04E9C5FAB3199E/1";
	}
	@RequestMapping("/loadImage")
	@ResponseBody
	public Map<String, Object> loadImage(@RequestParam MultipartFile file,HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(file);
		String filename = null;
		if(!ObjectUtils.isEmpty(file)) {
			try {
				InputStream is = file.getInputStream();
				//保存的位置
				String path = req.getServletContext().getRealPath("/images");
				System.out.println(path);
				File fileSave = new File(path);
				if(!fileSave.exists()){
					fileSave.mkdirs();
				}
				System.out.println(fileSave);
				filename = CommonUtils.uuid()+file.getOriginalFilename();
				OutputStream os = new FileOutputStream(new File(fileSave,filename));
				IOUtils.copy(is, os);
				is.close();
				os.close();
				map.put("flag", true);
				map.put("url", "/images/"+filename);
			} catch (IOException e) {
				map.put("flag", false);
			}

			
			return map;
		}
		map.put("flag", false);
		return map;
	}
	
	
	@RequestMapping("/insert")
	public String insert(Model model,Book book) {
		System.out.println(book);
		if(!ObjectUtils.isEmpty(book)) {
			try {
				if(bookService.insertBook(book)) {
					model.addAttribute("msg", "添加成功！");
				}
			} catch (Exception e) {
				model.addAttribute("msg", "添加失败！");
			}
			
			
		}else {
			model.addAttribute("msg", "添加失败！");
		}
		
		return "forward:/admin/book/list/5F79D0D246AD4216AC04E9C5FAB3199E/1";
	}
}
