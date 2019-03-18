package org.nz.service;

import java.util.List;

import org.nz.bean.Category;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月28日 上午11:49:09
* 类说明：
*
*/


public interface CategoryService {
	
	/**
	 * 所有图书分类
	 * @return
	 */
	List<Category> findAll();

	boolean insertCategory(Category category);

	List<Category> selectByPid(String pid);

	Category selectById(String cid);

	boolean updateCategory(Category category);

	boolean deleteById(String cid);

	
	
}
