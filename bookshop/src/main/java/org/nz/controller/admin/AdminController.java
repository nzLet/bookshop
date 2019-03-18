package org.nz.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.nz.bean.Admin;
import org.nz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;


/**
* @author 作者 : YN
* @version 创建时间：2019年2月14日 上午9:35:20
* 类说明：
*
*/
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/login")
	public String login(Admin admin,HttpServletRequest request) {
		String msg = null;
		
		if(admin.getAdminname().isEmpty()) {
			msg = "用户名不能为空！";
		}else if(admin.getAdminpwd().isEmpty()) {
			msg = "密码不能为空";
		}else{
			Admin admin2 = adminService.selectByName(admin.getAdminname());
			if(ObjectUtils.isEmpty(admin2)) {
				msg = "用户名不存在！";
			}else if(!admin.getAdminpwd().equals(admin2.getAdminpwd())) {
				msg = "密码错误！";
			}else {
				request.getSession().setAttribute("admin", admin2);
				return "forward:/view/admin/init";
			}
		}
		request.setAttribute("errorMsg", msg);
		return "forward:/view/admin";
	}
}
