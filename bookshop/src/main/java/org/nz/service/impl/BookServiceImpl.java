package org.nz.service.impl;

import java.util.List;

import org.nz.bean.Book;
import org.nz.dao.BookMapper;
import org.nz.service.BookService;
import org.nz.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月28日 下午3:38:47
* 类说明：
*
*/
@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookMapper bookMapper;

	@Override
	public List<Book> findBookByCategoryId(String field) {
		return bookMapper.findBookByCategoryId(field);
	}

	@Override
	public Book findBookById(String bid) {
		return bookMapper.selectByPrimaryKey(bid);
	}

	@Override
	public boolean updateBook(Book book) {
		return bookMapper.updateByPrimaryKeySelective(book)>0;
	}

	@Override
	public boolean deleteBook(String bid) {
		return bookMapper.deleteByPrimaryKey(bid)>0;
	}

	@Override
	public boolean insertBook(Book book) {
		book.setBid(CommonUtils.uuid());
		
		return bookMapper.insertSelective(book)>0;
	}
	
	
}
