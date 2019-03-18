package org.nz.dao;

import java.util.List;

import org.nz.bean.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(String cid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String cid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

	List<Category> findAll();

	List<Category> selectByPid(String pid);
}