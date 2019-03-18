package org.nz.dao;


import java.util.List;

import org.nz.bean.Cartitem;

public interface CartitemMapper {
    int deleteByPrimaryKey(String cartItemId);

    int insert(Cartitem record);

    int insertSelective(Cartitem record);

    Cartitem selectByPrimaryKey(String cartItemId);

    int updateByPrimaryKeySelective(Cartitem record);

    int updateByPrimaryKey(Cartitem record);

	List<Cartitem> selectAllByUid(String uid);

	int deleteByBid(String bid);

	Cartitem selectAllByBid(Cartitem cartitem);

	boolean deleteByIds(String[] cartItemIds);

	List<Cartitem> selectByIds(String[] cartItemId);
}