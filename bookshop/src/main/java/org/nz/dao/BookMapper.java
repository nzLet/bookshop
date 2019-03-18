package org.nz.dao;

import java.util.List;

import org.nz.bean.Book;

public interface BookMapper {
    int deleteByPrimaryKey(String bid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

	List<Book> findBookByCategoryId(String field);
}