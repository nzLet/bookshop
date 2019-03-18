package org.nz.bean;

import java.math.BigDecimal;

public class Book {
    private String bid;

    private String bname;

    private String author;

    private BigDecimal price;

    private BigDecimal currPrice;

    private BigDecimal discount;

    private String press;

    private String publishtime;

    private Integer edition;

    private Integer pageNum;

    private Integer wordNum;

    private String printtime;

    private Integer booksize;

    private String paper;

    private String cid;

    private String image_w;

    private String image_b;

    private Integer orderBy;
    
    private String pid;
    
    

    public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname == null ? null : bname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(BigDecimal currPrice) {
        this.currPrice = currPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime == null ? null : publishtime.trim();
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getWordNum() {
        return wordNum;
    }

    public void setWordNum(Integer wordNum) {
        this.wordNum = wordNum;
    }

    public String getPrinttime() {
        return printtime;
    }

    public void setPrinttime(String printtime) {
        this.printtime = printtime == null ? null : printtime.trim();
    }

    public Integer getBooksize() {
        return booksize;
    }

    public void setBooksize(Integer booksize) {
        this.booksize = booksize;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper == null ? null : paper.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getImage_w() {
        return image_w;
    }

    public void setImage_w(String image_w) {
        this.image_w = image_w == null ? null : image_w.trim();
    }

    public String getImage_b() {
        return image_b;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b == null ? null : image_b.trim();
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", author=" + author + ", price=" + price + ", currPrice="
				+ currPrice + ", discount=" + discount + ", press=" + press + ", publishtime=" + publishtime
				+ ", edition=" + edition + ", pageNum=" + pageNum + ", wordNum=" + wordNum + ", printtime=" + printtime
				+ ", booksize=" + booksize + ", paper=" + paper + ", cid=" + cid + ", image_w=" + image_w + ", image_b="
				+ image_b + ", orderBy=" + orderBy + ", pid=" + pid + "]";
	}
    
}