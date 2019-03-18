package org.nz.bean;

import java.util.Arrays;
import java.util.List;

/**
 * @author 作者 : YN
 * @version 创建时间：2019年2月16日 下午4:18:51 类说明：
 *
 */
public class Pager {
	private Integer pageNum; //当前页
	private Integer pageSize; //每页显示的条数
	private Integer size; //
	private Integer startRow; //开始第几个
	private Integer endRow; //结束第几个
	private Integer total; //总条数
	private Integer pages; //总页数
	private List<Object> list; //目标集合
	private Integer prePage; //上一页
	private Integer		nextPage;//下一页 
	boolean isFirstPage;  //是否为第一页
	boolean isLastPage;  //是否为最后一页
	boolean hasPreviousPage;  //是否有上一页
	boolean hasNextPage; //是否有下一页
	private Integer navigatePages; //显示的页数
	private Integer navigateFirstPage; //首页
	private Integer navigateLastPage; //尾页
	private int[] navigatepageNums; //要显示页数的数组
	
	
	public Integer getPrePage() {
		return prePage;
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public boolean isFirstPage() {
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public Integer getNavigatePages() {
		return navigatePages;
	}
	public void setNavigatePages(Integer navigatePages) {
		this.navigatePages = navigatePages;
	}
	public Integer getNavigateFirstPage() {
		return navigateFirstPage;
	}
	public void setNavigateFirstPage(Integer navigateFirstPage) {
		this.navigateFirstPage = navigateFirstPage;
	}
	public Integer getNavigateLastPage() {
		return navigateLastPage;
	}
	public void setNavigateLastPage(Integer navigateLastPage) {
		this.navigateLastPage = navigateLastPage;
	}
	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}
	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Pager [pageNum=" + pageNum + ", pageSize=" + pageSize + ", size=" + size + ", startRow=" + startRow
				+ ", endRow=" + endRow + ", total=" + total + ", pages=" + pages + ", list=" + list + ", prePage="
				+ prePage + ", nextPage=" + nextPage + ", isFirstPage=" + isFirstPage + ", isLastPage=" + isLastPage
				+ ", hasPreviousPage=" + hasPreviousPage + ", hasNextPage=" + hasNextPage + ", navigatePages="
				+ navigatePages + ", navigateFirstPage=" + navigateFirstPage + ", navigateLastPage=" + navigateLastPage
				+ ", navigatepageNums=" + Arrays.toString(navigatepageNums) + "]";
	}
	
	
}
