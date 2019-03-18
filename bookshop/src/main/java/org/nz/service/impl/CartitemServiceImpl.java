package org.nz.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.nz.bean.Cartitem;
import org.nz.dao.CartitemMapper;
import org.nz.service.CartitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月11日 下午1:31:19
* 类说明：
*
*/
@Service
public class CartitemServiceImpl implements CartitemService{

	@Autowired
	CartitemMapper cartitemMapper;

	@Override
	public boolean addCartitem(Cartitem cartitem) {
		//查询是否有同一个图书的订单
		Cartitem cartitem2 = cartitemMapper.selectAllByBid(cartitem);
		if(ObjectUtils.isEmpty(cartitem2)) {
			return cartitemMapper.insert(cartitem)>0;
		}else {
			cartitem2.setQuantity(cartitem.getQuantity()+cartitem2.getQuantity());
			return cartitemMapper.updateByPrimaryKey(cartitem2)>0;
		}
	}

	@Override
	public List<Cartitem> selectAllByUid(String uid) {
		
		List<Cartitem> cartitems = cartitemMapper.selectAllByUid(uid);

		BigDecimal total=null;
		for (Cartitem cartitem : cartitems) {
				BigDecimal quantity = new BigDecimal(cartitem.getQuantity());
				total=cartitem.getBook().getCurrPrice().multiply(quantity);
				cartitem.setSubtotal(total);
		}
		
		return cartitems;
	}

	@Override
	public boolean updateById(Cartitem cartitem) {
		return cartitemMapper.updateByPrimaryKeySelective(cartitem)>0;
	}

	@Override
	public Cartitem selectById(String cartItemId) {
		return cartitemMapper.selectByPrimaryKey(cartItemId);
	}

	@Override
	public boolean deleteByBid(String cartItemId) {
		
		
		return cartitemMapper.deleteByPrimaryKey(cartItemId)>0;
	}

	@Override
	public boolean deleteByIds(String[] cartItemIds) { 
		
		return cartitemMapper.deleteByIds(cartItemIds);
	}

	@Override
	public List<Cartitem> selectByIds(String[] cartItemId) {
		List<Cartitem> cartitems = cartitemMapper.selectByIds(cartItemId);
		BigDecimal total=null;
		for (Cartitem cartitem : cartitems) {
				BigDecimal quantity = new BigDecimal(cartitem.getQuantity());
				total=cartitem.getBook().getCurrPrice().multiply(quantity);
				cartitem.setSubtotal(total);
				
		}
		
		return cartitems; 
	}

}
