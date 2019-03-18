package org.nz.service.impl;




import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nz.bean.Cartitem;
import org.nz.bean.Order;
import org.nz.bean.Orderitem;
import org.nz.dao.OrderMapper;
import org.nz.dao.OrderitemMapper;
import org.nz.service.CartitemService;
import org.nz.service.OrderService;
import org.nz.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月12日 上午9:20:19
* 类说明：
*
*/
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	CartitemService cartitemService;
	
	@Autowired
	OrderitemMapper orderitemMapper;
	
	@Override
	public Order insertOrder(String[] cartItemId,String address, String total,String uid) {
		List<Cartitem> cartitems = cartitemService.selectByIds(cartItemId);
		if(cartitems.size()==0) {
			return null;
		}
		Order order=new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(String.format("%tF %<tT", new Date()));
		order.setTotal(new BigDecimal(total));
		order.setStatus(1);
		order.setAddress(address);
		order.setUid(uid);
		
		List<Orderitem> orderItemList = new ArrayList<Orderitem>();
		for (Cartitem cartitem : cartitems) {
			Orderitem orderitem = new Orderitem();
			orderitem.setOrderItemId(CommonUtils.uuid());
			orderitem.setQuantity(cartitem.getQuantity());
			orderitem.setSubtotal(cartitem.getSubtotal());
			orderitem.setBid(cartitem.getBook().getBid());
			orderitem.setBname(cartitem.getBook().getBname());
			orderitem.setCurrPrice(cartitem.getBook().getCurrPrice());
			orderitem.setImage_b(cartitem.getBook().getImage_b());
			orderitem.setOid(order.getOid());
			orderItemList.add(orderitem);
		}
		order.setOrderItemList(orderItemList);
		if(orderMapper.insert(order)>0) {
			for (Orderitem orderitem : orderItemList) {
				if(orderitemMapper.insert(orderitem)<0) {
					return null;
				}
			}
			if(cartitemService.deleteByIds(cartItemId)) {
				return order;
			}
			
		}
		
		return null;
	}

	@Override
	public Order selectById(String oid) {
		return orderMapper.selectByPrimaryKey(oid);
	}

	@Override
	public boolean updateStatus(Order order) {
		
		
		return orderMapper.updateByPrimaryKeySelective(order)>0;
	}

	@Override
	public List<Order> selectbyUid(String uid) {
		
		
		return orderMapper.selectbyUid(uid);
	}

	@Override
	public List<Order> selectAll() {
		return orderMapper.selectAll();
	}

	@Override
	public List<Order> selectByStatus(String status) {
		return orderMapper.selectByStatus(status);
	}

}
