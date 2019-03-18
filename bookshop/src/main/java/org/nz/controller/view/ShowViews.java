package org.nz.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月28日 上午9:45:07
* 类说明：
*
*/
@Controller
@RequestMapping("/view")
public class ShowViews {
	@RequestMapping("/init")
	public String init() {
		System.out.println("进入系统");
		return "front/jsps/main";
	}
	@RequestMapping("/top")
	public String top() {
		
		return "front/jsps/top";
	}
	@RequestMapping("/search")
	public String serach() {
		
		return "front/jsps/search";
	}
	@RequestMapping("/body")
	public String body() {
		
		return "front/jsps/body";
	}
	@RequestMapping("/login")
	public String login() {
		
		return "front/jsps/user/login";
		
	}
	@RequestMapping("/quit")
	public String quit(HttpServletRequest request) {
		request.getSession().invalidate();
		return "forward:/view/init";
	}
	@RequestMapping("/updatePwd")
	public String updatePass() {
		
		return "front/jsps/user/pwd";
	}
	@RequestMapping("/regist")
	public String regist() {
		
		return "front/jsps/user/regist";
	}
	@RequestMapping("/admin")
	public String admin() {
		
		return "admin/adminjsps/login";
	}
	@RequestMapping("/admin/init")
	public String adminInit() {
		
		return "admin/adminjsps/admin/main";
	}
	@RequestMapping("/admin/top")
	public String adminTop() {
		
		return "admin/adminjsps/admin/top";
	}
	@RequestMapping("/admin/body")
	public String adminBody() {
		
		return "admin/adminjsps/admin/body";
	}
	@RequestMapping("/admin/quit")
	public String adminQuit(HttpServletRequest request) {
		request.getSession().invalidate();
		return "forward:/view/admin";
	}
	@RequestMapping("/admin/book")
	public String AdminBook() {
		
		return "admin/adminjsps/admin/book/main";
	}
	@RequestMapping("/admin/book/body")
	public String adminBookBody() {
		
		return "admin/adminjsps/admin/book/body";
	}
	@RequestMapping("/admin/book/search")
	public String adminBookSearch() {
		
		return "admin/adminjsps/admin/book/gj";
	}
}
