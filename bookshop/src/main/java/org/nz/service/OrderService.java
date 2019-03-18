package org.nz.service;

import java.util.List;

import org.nz.bean.Order;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月12日 上午9:05:51
* 类说明：
*
*/
public interface OrderService {

	Order insertOrder(String[] cartItemId, String address, String total, String uid);

	Order selectById(String oid);

	boolean updateStatus(Order order);

	List<Order> selectbyUid(String uid);

	List<Order> selectAll();

	List<Order> selectByStatus(String status);

}
