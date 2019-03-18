package org.nz.service;

import java.util.List;

import org.nz.bean.Book;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月28日 下午3:38:17
* 类说明：
*
*/
public interface BookService {

	List<Book> findBookByCategoryId(String field);

	Book findBookById(String bid);

	boolean updateBook(Book book);

	boolean deleteBook(String bid);

	boolean insertBook(Book book);

}
