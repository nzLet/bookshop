package org.nz.dao;

import org.nz.bean.Orderitem;

public interface OrderitemMapper {
    int deleteByPrimaryKey(String orderItemId);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    Orderitem selectByPrimaryKey(String orderItemId);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);
}