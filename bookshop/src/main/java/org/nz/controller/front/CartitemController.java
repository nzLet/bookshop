package org.nz.controller.front;


import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nz.bean.Cartitem;
import org.nz.bean.User;
import org.nz.service.BookService;
import org.nz.service.CartitemService;
import org.nz.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 作者 : YN
 * @version 创建时间：2019年2月11日 下午1:21:58 类说明：
 *
 */
@Controller
@RequestMapping("/front/cartitem")
public class CartitemController {
	@Autowired
	CartitemService cartitemService;
	@Autowired
	BookService bookService;

	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		System.out.println("=============Cartitem/show===========");
		User user = (User)request.getSession().getAttribute("user");
		System.out.println(user);
		if(!ObjectUtils.isEmpty(user)) {
			List<Cartitem> cartitems = cartitemService.selectAllByUid(user.getUid());
			request.setAttribute("cartitems", cartitems);
			return "/front/jsps/cart/list";
		}

		return "";
	}

	@RequestMapping("/add/{bid}/{orderBy}")
	public String add(@PathVariable("bid") String bid,@PathVariable("orderBy") String orderBy,
			String quantity,HttpServletRequest request) {
		System.out.println("=============Cartitem/add===========");
		User user = (User)request.getSession().getAttribute("user");
		Cartitem cartitem = new Cartitem();
		if(bid!=null&&!ObjectUtils.isEmpty(user)&&orderBy!=null&&quantity!=null) {
			cartitem.setBid(bid);
			cartitem.setUid(user.getUid());
			cartitem.setCartItemId(CommonUtils.uuid());
			cartitem.setQuantity(Integer.parseInt(quantity));
			cartitem.setOrderBy(Integer.parseInt(orderBy));
			if(cartitemService.addCartitem(cartitem)) {
				return "forward:/front/cartitem/show";
			}
			
		}
		return "forward:/cartitem/show";
	}
	@RequestMapping("/updateNum")
	@ResponseBody
	public Cartitem updateNum(Cartitem cartitem){
		System.out.println("=============Cartitem/updatenum===========");
		if(cartitemService.updateById(cartitem)) {
			cartitem = cartitemService.selectById(cartitem.getCartItemId());
			System.out.println(cartitem);
			BigDecimal quantity = new BigDecimal(cartitem.getQuantity());
			cartitem.setSubtotal(cartitem.getBook().getCurrPrice().multiply(quantity));
			return cartitem;
		}
		
		return null;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(String cartItemId) {
		System.out.println("=========delete====id="+cartItemId+"======");
		
		if(cartitemService.deleteByBid(cartItemId)) {
			return true;
		}

		return false;
	}
	@RequestMapping("/deleteMore/{cartItemId}")
	public String deleteMore(@PathVariable("cartItemId") String cartItemId,Model model) {
		System.out.println(cartItemId);
		String[] cartItemIds = cartItemId.split(",");
		System.out.println(cartItemIds.toString());
		if(cartitemService.deleteByIds(cartItemIds)) {
			model.addAttribute("msg", "删除成功！");
		}else {
			model.addAttribute("msg", "删除失败！");
		}
		return "forward:/front/cartitem/show";
	}
	@RequestMapping("/loadCartItems")
	public String loadCartItems(String cartItemIds,String total,Model model) {
		System.out.println(cartItemIds+"======"+total);
		String[] cartItemId = cartItemIds.split(",");
		List<Cartitem> cartitems = cartitemService.selectByIds(cartItemId);
		model.addAttribute("cartItemIds", cartItemIds);
		model.addAttribute("total", total);
		model.addAttribute("cartitems", cartitems);
		return "/front/jsps/cart/showitem";
	}
	
}
