package org.nz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.nz.bean.Category;
import org.nz.dao.CategoryMapper;
import org.nz.service.CategoryService;
import org.nz.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author 作者 : YN
* @version 创建时间：2019年1月28日 上午11:50:16
* 类说明：
*
*/
@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private  CategoryMapper categoryMapper;
	
	@Override
	public List<Category> findAll() {
		
		List<Category> categories =  categoryMapper.findAll();
		List<Category> parents = new ArrayList<>();
		
		// 得到一级标题
		for (Category category1 : categories) {
			
			if(category1.getPid()==null) {
				parents.add(category1);
			}
		}
		System.out.println(parents);
		System.out.println("================");
		//每一级标题下的子标题
		for (Category parent : parents) {
			List<Category> children = new ArrayList<>();
			for (Category category2 : categories) {
				if(parent.getCid().equals(category2.getPid())) {
					
					children.add(category2);
				}
			}
			parent.setChildren(children);
		}
		System.out.println(parents);
		return parents;
	}

	@Override
	public boolean insertCategory(Category category) {
		category.setOrderBy(0);
		category.setCid(CommonUtils.uuid());
		System.out.println(category);
		return categoryMapper.insert(category)>0;
	}

	@Override
	public List<Category> selectByPid(String pid) {
		
		return categoryMapper.selectByPid(pid);
	}

	@Override
	public Category selectById(String cid) {
		return categoryMapper.selectByPrimaryKey(cid);
	}

	@Override
	public boolean updateCategory(Category category) {
		return categoryMapper.updateByPrimaryKeySelective(category)>0;
	}

	@Override
	public boolean deleteById(String cid) {
		return categoryMapper.deleteByPrimaryKey(cid)>0;
	}
	
}
