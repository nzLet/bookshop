package org.nz.dao;

import java.util.List;

import org.nz.bean.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(String oid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	int insertOrder(Order order);

	List<Order> selectbyUid(String uid);

	List<Order> selectAll();

	List<Order> selectByStatus(String status);
}