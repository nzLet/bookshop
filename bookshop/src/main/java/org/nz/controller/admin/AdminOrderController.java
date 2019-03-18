package org.nz.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nz.bean.Order;
import org.nz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月16日 上午10:45:25
* 类说明：
*
*/
@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping("/list/{pn}")
	public String list(HttpServletRequest request,@PathVariable("pn") int pn) {
		System.out.println(pn);
		PageHelper.startPage(pn,8);
			List<Order> orders= orderService.selectAll();
			PageInfo<Order> pages = new PageInfo<>(orders,5);
			request.setAttribute("pages", pages);
			System.out.println(pages);
			return "/admin/adminjsps/admin/order/list";
		
		
	}
	@RequestMapping("/listByStatus/{status}/{pn}")
	public String listByStatus(@PathVariable("status") String status,@PathVariable("pn") int pn,Model model) {
		System.out.println(pn);
		System.out.println(status);
		PageHelper.startPage(pn,8);
		if(!StringUtils.isEmpty(status)) { 
			List<Order> orders= orderService.selectByStatus(status);
			PageInfo<Order> pages = new PageInfo<>(orders,5);
			model.addAttribute("pages", pages);
			System.out.println(pages);
			return "/admin/adminjsps/admin/order/list";
		}
		return "forward:/admin/order/list/1";
	}
	@RequestMapping("/detail/{oid}/{btn}")
	public String detail(@PathVariable("oid") String oid,@PathVariable("btn") String btn,
			Model model) {
		Order order = orderService.selectById(oid);
		System.out.println(btn);
		if(!ObjectUtils.isEmpty(order)) {
			model.addAttribute("order", order);
			model.addAttribute("btn", btn);
			return "/admin/adminjsps/admin/order/desc";
		}
		return "forward:/admin/order/list/1";
	}
	@RequestMapping("/confirm/{oid}")
	public String confirm(@PathVariable("oid") String oid,Model model) {
		Order order = new Order();
		order.setOid(oid);
		order.setStatus(3);
		if(orderService.updateStatus(order)) {
			model.addAttribute("msg", "操作成功！");
		}else {
			model.addAttribute("msg", "操作失败，请重新操作！");
		}
		
		return "forward:/admin/order/list/1";
	}
}
