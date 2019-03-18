package org.nz.controller.front;


import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nz.bean.Order;
import org.nz.bean.User;
import org.nz.service.OrderService;
import org.nz.utils.PaymentUtil;
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
 * @version 创建时间：2019年2月12日 上午9:00:54 类说明：
 *
 */
@Controller
@RequestMapping("/front/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@RequestMapping("/insertOrder")
	public String insertOrder(String cartItemIds, String address, String total, HttpServletRequest request) {
		System.out.println(cartItemIds + "," + address + "," + total);
		String[] cartItemId = cartItemIds.split(",");
		User user = (User) request.getSession().getAttribute("user");
		if (ObjectUtils.isEmpty(user)) {
			return "forward:/view/init";
		} else {
			Order order = orderService.insertOrder(cartItemId, address, total, user.getUid());
			if (ObjectUtils.isEmpty(order)) {
				request.setAttribute("code", "error");
				request.setAttribute("msg", "您没有选择要购买的图书，不能下单！");
				return "/front/jsps/msg";
			} else {
				request.setAttribute("order", order);
				return "/front/jsps/order/ordersucc";
			}
		}
	}

	@RequestMapping("/pay/{oid}")
	public String pay(@PathVariable("oid") String oid, Model model) {
		Order order = orderService.selectById(oid);

		model.addAttribute("order", order);
		return "/front/jsps/order/pay";
	}

	@RequestMapping("/checkType")
	public String checkType(String oid, String yh) {
		Properties props = new Properties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 1. 准备13个参数
		 */
		String p0_Cmd = "Buy";// 业务类型，固定值Buy
		String p1_MerId = props.getProperty("p1_MerId");// 商号编码，在易宝的唯一标识
		String p2_Order = oid;// 订单编码
		String p3_Amt = "0.01";// 支付金额
		String p4_Cur = "CNY";// 交易币种，固定值CNY
		String p5_Pid = "";// 商品名称
		String p6_Pcat = "";// 商品种类
		String p7_Pdesc = "";// 商品描述
		String p8_Url = props.getProperty("p8_Url");// 在支付成功后，易宝会访问这个地址。
		String p9_SAF = "";// 送货地址
		String pa_MP = "";// 扩展信息
		String pd_FrpId = yh;// 支付通道
		String pr_NeedResponse = "1";// 应答机制，固定值1

		/*
		 * 2. 计算hmac 需要13个参数 需要keyValue 需要加密算法
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

		/*
		 * 3. 重定向到易宝的支付网关
		 */
		StringBuilder sb = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node");
		sb.append("?").append("p0_Cmd=").append(p0_Cmd);
		sb.append("&").append("p1_MerId=").append(p1_MerId);
		sb.append("&").append("p2_Order=").append(p2_Order);
		sb.append("&").append("p3_Amt=").append(p3_Amt);
		sb.append("&").append("p4_Cur=").append(p4_Cur);
		sb.append("&").append("p5_Pid=").append(p5_Pid);
		sb.append("&").append("p6_Pcat=").append(p6_Pcat);
		sb.append("&").append("p7_Pdesc=").append(p7_Pdesc);
		sb.append("&").append("p8_Url=").append(p8_Url);
		sb.append("&").append("p9_SAF=").append(p9_SAF);
		sb.append("&").append("pa_MP=").append(pa_MP);
		sb.append("&").append("pd_FrpId=").append(pd_FrpId);
		sb.append("&").append("pr_NeedResponse=").append(pr_NeedResponse);
		sb.append("&").append("hmac=").append(hmac);

		return "redirect:" + sb.toString();
	}

	@RequestMapping("/back")
	public String back(HttpServletRequest req, HttpServletResponse resp) {
		String p1_MerId = req.getParameter("p1_MerId");
		String r0_Cmd = req.getParameter("r0_Cmd");
		String r1_Code = req.getParameter("r1_Code");
		String r2_TrxId = req.getParameter("r2_TrxId");
		String r3_Amt = req.getParameter("r3_Amt");
		String r4_Cur = req.getParameter("r4_Cur");
		String r5_Pid = req.getParameter("r5_Pid");
		String r6_Order = req.getParameter("r6_Order");
		String r7_Uid = req.getParameter("r7_Uid");
		String r8_MP = req.getParameter("r8_MP");
		String r9_BType = req.getParameter("r9_BType");
		String hmac = req.getParameter("hmac");
		/*
		 * 2. 获取keyValue
		 */
		Properties props = new Properties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String keyValue = props.getProperty("keyValue");
		/*
		 * 3. 调用PaymentUtil的校验方法来校验调用者的身份 >如果校验失败：保存错误信息，转发到msg.jsp >如果校验通过： *
		 * 判断访问的方法是重定向还是点对点，如果要是重定向 修改订单状态，保存成功信息，转发到msg.jsp * 如果是点对点：修改订单状态，返回success
		 */
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid,
				r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		if (!bool) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "无效的签名，支付失败！（你不是好人）");
			return "/front/jsps/msg";
		}
		if (r1_Code.equals("1")) {
			Order order = new Order();
			order.setOid(r6_Order);
			order.setStatus(2);
			if (orderService.updateStatus(order)) {
				if (r9_BType.equals("1")) {
					req.setAttribute("code", "success");
					req.setAttribute("msg", "恭喜，支付成功！");
					return "/front/jsps/msg";
				} else if (r9_BType.equals("2")) {
					try {
						resp.getWriter().print("success");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	@RequestMapping("/list/{pn}")
	public String show(HttpServletRequest request,@PathVariable("pn") int pn) {
		System.out.println(pn);
		PageHelper.startPage(pn,8);
		User user = (User)request.getSession().getAttribute("user");
		if(!ObjectUtils.isEmpty(user)) {
			
			List<Order> orders= orderService.selectbyUid(user.getUid());
			
			PageInfo<Order> pages = new PageInfo<>(orders,5);
			
			request.setAttribute("pages", pages);
			System.out.println(pages);
			return "/front/jsps/order/list";
		}
		
		return null;
	}
	@RequestMapping("/detail/{oid}/{btn}")
	public String detail(@PathVariable("oid") String oid,@PathVariable("btn") String btn,Model model) {
		Order order = orderService.selectById(oid);
		System.out.println(btn);
		if(!ObjectUtils.isEmpty(order)) {
			model.addAttribute("order", order);
			model.addAttribute("btn", btn);
			return "/front/jsps/order/desc";
		}
		return "";
	}
	@RequestMapping("/cancel/{oid}")
	public String cancel(@PathVariable("oid") String oid) {
		Order order = new Order();
		order.setOid(oid);
		order.setStatus(5);
		if(orderService.updateStatus(order)) {
			return "forward:/order/list/1";
		}
		return null;
	}
	
	@RequestMapping("confirm/{oid}")
	public String confirm(@PathVariable("oid") String oid) {
		Order order = new Order();
		order.setOid(oid);
		order.setStatus(4);
		if(orderService.updateStatus(order)) {
			return "forward:/order/list/1";
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
