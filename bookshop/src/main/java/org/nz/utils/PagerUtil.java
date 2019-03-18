package org.nz.utils;

import java.util.List;

import org.nz.bean.Pager;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月16日 下午4:06:25
* 类说明：分页工具
*
*/
public class PagerUtil {
	
	/**
	 * 
	 * @param list 
	 * @param pageSize 每页显示的条数
	 * @param pageNum 当前页
	 * @param navigatePages 显示的页数
	 * @return
	 */
	public Pager getPage(List<Object> list,int pageSize,int pageNum,Integer navigatePages) {
		Pager pager = new Pager();
		if(pageNum==0) {
			pager.setPageNum(1);
		}else {
			pager.setPageNum(pageNum);
		}
		pager.setPageSize(pageSize);
		pager.setNavigatePages(navigatePages);
		pager.setTotal(list.size());
		pager.setPages((int) Math.ceil(pager.getTotal()/pageSize));
		pager.setStartRow((pager.getPageNum()-1)*pageSize+1);
		pager.setEndRow(pager.getPageNum()*pageSize);
		
		return pager;
	}
	
	
}
