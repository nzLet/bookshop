package org.nz.controller.front;


import java.util.List;

import org.nz.bean.Category;
import org.nz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月28日 上午11:42:54
* 类说明：
*
*/
@Controller
@RequestMapping("/front/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/list")
	public String findAll(Model model) {
		
		List<Category> parents = categoryService.findAll();
		model.addAttribute("parents", parents);
		//return parents;
		return "/front/jsps/left";
	}
	
	
}
