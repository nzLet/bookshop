package org.nz.service;


import java.util.List;

import org.nz.bean.Cartitem;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月11日 下午1:23:14
* 类说明：
*
*/
public interface CartitemService {

	boolean addCartitem(Cartitem cartitem);

	List<Cartitem> selectAllByUid(String uid);

	boolean updateById(Cartitem cartitem);

	Cartitem selectById(String cartItemId);

	boolean deleteByBid(String cartItemId);

	boolean deleteByIds(String[] cartItemIds);

	List<Cartitem> selectByIds(String[] cartItemId);
	


}
