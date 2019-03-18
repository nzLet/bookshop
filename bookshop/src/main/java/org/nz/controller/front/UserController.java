package org.nz.controller.front;


import javax.servlet.http.HttpServletRequest;

import org.nz.bean.User;
import org.nz.service.PropertyService;
import org.nz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月29日 下午8:24:42
* 类说明：
*
*/
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PropertyService propertyService;
	/**
	 * 
	 * @param request
	 * @param verifyCode 验证码
	 * @return
	 * 检验验证码
	 */
	@RequestMapping("/checkCode")
	@ResponseBody
	public boolean checkCode(HttpServletRequest request,String verifyCode) {
		String vCode = (String) request.getSession().getAttribute("vCode");
		System.out.println(vCode);
		System.out.println(verifyCode);
		if(verifyCode!=null&&verifyCode.equalsIgnoreCase(vCode)) {
			
			return true;
		}
		
		
		return false;
	}
	/*@RequestMapping("/checkAll")
	@ResponseBody
	public Map<String, Object> checkAll(User cUser,HttpServletRequest request){
		
		System.out.println("验证开始"+cUser);
		Map<String, Object> map = new HashMap<String, Object>();
		if(ObjectUtils.isEmpty(cUser)) {
			map.put("flag", false);
			map.put("msg", "登录信息不能为空！");
		}else {
			User user = userService.findByName(cUser.getLoginname());
			if(ObjectUtils.isEmpty(user)) {
				map.put("flag", false);
				map.put("msg", "用户名不存在！");
			}else if(!user.getLoginpass().equals(cUser.getLoginpass())){
				map.put("flag", false);
				map.put("msg", "密码错误！");
			}else if(!cUser.getVerifyCode().equalsIgnoreCase((String)request.getSession().getAttribute("vCode"))){
				map.put("flag", false);
				map.put("msg", "验证码错误！");
			}else {
				map.put("flag", true);
				request.getSession().setAttribute("user", user);
			}
			
		}
		System.out.println(map);
		return map;
	}*/
	@RequestMapping("/login")
	public String checkAll(User cUser,HttpServletRequest request){
		
		System.out.println("验证开始"+cUser);
		
		String msg=null;
		if(ObjectUtils.isEmpty(cUser)) {
			msg="登录信息不能为空！";
		}else {
			User user = userService.findByName(cUser.getLoginname());
			if(ObjectUtils.isEmpty(user)) {
				msg="用户名不存在！";
			}else if(!user.getLoginpass().equals(cUser.getLoginpass())){
				msg= "密码错误！";
			}else if(!cUser.getVerifyCode().equalsIgnoreCase((String)request.getSession().getAttribute("vCode"))){
				msg= "验证码错误！";
			}else {
				request.getSession().setAttribute("user", user);
				return "forward:/view/init";
			}
			
		}
		System.out.println(msg);
		request.setAttribute("errorMsg", msg);
		return "forward:/view/login";
	}
	@RequestMapping("/updatePwd")
	public String updatePwd(HttpServletRequest request,User cUser) {
		User user = (User)request.getSession().getAttribute("user");
		String msg=null;
		if(ObjectUtils.isEmpty(user)) {
			msg="未登录，先登录！";
			request.setAttribute("errorMsg", msg);
			return "forward:/view/login";
		}else if(!cUser.getNewpass().equals(cUser.getReloginpass())) {
			msg= "两次密码不一致！";
		}else if(!userService.updateUser(cUser.getNewpass())){
			msg= "密码错误！";
		}else if(!cUser.getVerifyCode().equalsIgnoreCase((String)request.getSession().getAttribute("vCode"))){
			msg= "验证码错误！";
		}else {
			request.setAttribute("code", "success");
			request.setAttribute("msg", "修改成功！");
			request.getSession().invalidate();
			return "/front/jsps/msg";
			
		}
		request.setAttribute("errorMsg", msg);
		return "forward:/view/updatePass";
	}
	/**
	 * 检查用户名
	 * @param loginname
	 * @return
	 */
	@RequestMapping("/regist/checkName")
	@ResponseBody
	public boolean checkName(String loginname) {
		System.out.println(loginname);
		User user = userService.findByLoginName(loginname);
		if(ObjectUtils.isEmpty(user)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 检查email
	 * @param email
	 * @return
	 */
	@RequestMapping("/regist/checkEmail")
	@ResponseBody
	public boolean checkEmail(String email) {
		System.out.println(email);
		User user = userService.findByEmail(email);
		if(ObjectUtils.isEmpty(user)) {
			
			return true;
		}
		return false;
	}
	@RequestMapping("/regist")
	public String regist(User user,Model model) {
		String msg=null;
		if(user.getLoginname().isEmpty()) {
			msg = "用户名不能为空";
			
		}else if(user.getLoginpass().isEmpty()) {
			msg = "密码不能为空";
		}else if(!user.getLoginpass().equals(user.getReloginpass())) {
			msg = "两次密码不一致";
		}else if(user.getEmail().isEmpty()) {
			msg = "邮箱不能为空";
		}else if(user.getVerifyCode().isEmpty()) {
			msg = "验证码不能为空";
		}else if(userService.insertUser(user)){
			model.addAttribute("code", "success");
			model.addAttribute("msg", "注册成功，请打开邮箱进行激活");
			return "/front/jsps/msg";
		}else {
			msg = "注册失败";
		}
		model.addAttribute("errorMsg", msg);
		return "forward:/view/regist";
	}
	@RequestMapping("/active/{activationCode}")
	public String active(@PathVariable("activationCode") String activationCode,Model model) {
		if(userService.updateByActivationCode(activationCode,propertyService.VALID_TIME)) {
			model.addAttribute("code", "success");
			model.addAttribute("msg", "激活成功，请登录");
		}else {
			model.addAttribute("code", "error");
			model.addAttribute("msg", "激活码失效，请重新注册");
		}
		return "/front/jsps/msg";
	}
}
