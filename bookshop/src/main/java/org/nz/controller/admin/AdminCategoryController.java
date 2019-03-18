package org.nz.controller.admin;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nz.bean.Category;
import org.nz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月14日 上午11:14:33
* 类说明：
*
*/
@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

	@Autowired
	CategoryService categoryService;
	/**
	 * 分类列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Category> parents = categoryService.findAll();
		System.out.println(parents);
		model.addAttribute("parents", parents);
		return "admin/adminjsps/admin/category/list";
	}
	/**
	 * 跳转添加分类页面
	 * @param cid   分类id
	 * @param model
	 * @return
	 */
	@RequestMapping("/goAdd/{cid}")
	public String goAdd(@PathVariable("cid") String cid,Model model) {
		if(!cid.isEmpty()) {
			System.out.println("cid="+cid);
			if("0".equals(cid)) {
				return "admin/adminjsps/admin/category/add";
			}else {
				List<Category> parents = categoryService.findAll();
				model.addAttribute("parents", parents);
				model.addAttribute("cid", cid);
				return "admin/adminjsps/admin/category/add2";
			}
		}
		
		return "forward:/admin/category/list";
	}
	@RequestMapping("/insert/{type}")
	public String insert (@PathVariable("type") String type,Category category,Model model) {
		String msg = null;
		System.out.println(type);
		System.out.println(category);
		if(!type.isEmpty()&&!ObjectUtils.isEmpty(category)) { 
			if(type.equals("0")) {
				//添加一级标题
				System.out.println("添加一级标题");
				if(StringUtils.isEmpty(category.getPid())&&categoryService.insertCategory(category)) {
					msg="添加成功！";
				}
			}else if(type.equals("1")) {
				//添加二级标题
				System.out.println("添加二级标题");
				try {
					if(!ObjectUtils.isEmpty(categoryService.selectById(category.getPid()))&&categoryService.insertCategory(category)){
						msg="添加成功！";
					
					}
				} catch (Exception e) {
					msg = "添加失败！";
					
				}
				
			}
		}else {
			msg = "添加失败！";
		}
		model.addAttribute("msg", msg);
		return "forward:/admin/category/list";
	}
	@RequestMapping("/goUpdate/{cid}")
	public String goUpdate(@PathVariable("cid") String cid,Model model) {
		if(!cid.isEmpty()) {
			Category category = categoryService.selectById(cid);
			List<Category> parents = categoryService.findAll();
			if(!ObjectUtils.isEmpty(category)&&parents.size()!=0) {
				model.addAttribute("category", category);
				model.addAttribute("parents", parents);
				if(StringUtils.isEmpty(category.getPid())) {
					return "admin/adminjsps/admin/category/edit";
				}else {
					return "admin/adminjsps/admin/category/edit2";
				}
			}
		}
		return "forward:/admin/category/list";
	}
	@RequestMapping("/update")
	public String update(Category category,Model model) {
		System.out.println(category);
		if(!ObjectUtils.isEmpty(category)) {
			try {
				if(categoryService.updateCategory(category)) {
					model.addAttribute("msg", "修改成功");
					return "forward:/admin/category/list";
				}
			} catch (Exception e) {
				model.addAttribute("msg", "修改失败,分类不能重复！");
				return "forward:/admin/category/list";
			}
		}
		model.addAttribute("msg", "修改失败");
		return "forward:/admin/category/list";
	}
	@RequestMapping("/delete/{cid}")
	public String delete(@PathVariable("cid") String cid,Model model) {
		if(!cid.isEmpty()) {
			try {
				if(categoryService.deleteById(cid)) {
					model.addAttribute("msg", "删除成功");
					return "forward:/admin/category/list";
				}
			} catch (Exception e) {
				
			}
		}
		model.addAttribute("msg", "删除失败");
		return "forward:/admin/category/list";
	}
	@RequestMapping("/left")
	public String findAll(Model model) {
		
		List<Category> parents = categoryService.findAll();
		model.addAttribute("parents", parents);
		//return parents;
		return "admin/adminjsps/admin/book/left";
	}
}
