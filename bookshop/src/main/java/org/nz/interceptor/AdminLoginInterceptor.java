package org.nz.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nz.bean.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月29日 下午7:02:23
* 类说明：
*
*/
public class AdminLoginInterceptor implements HandlerInterceptor{
	private Logger logger = LoggerFactory.getLogger(AdminLoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("====进入AdminLoginInterceptor====");
		//从session获得user
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(ObjectUtils.isEmpty(admin)) {
			//没登录，跳转到需要登录界面
			logger.info("=========未登录=============");
			request.setAttribute("code", "error");
			request.setAttribute("msg", "未登录，不能进行此次访问！请前往登录页面登录。");
			request.getRequestDispatcher("/WEB-INF/pages/admin/adminjsps/msg.jsp").forward(request, response);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
