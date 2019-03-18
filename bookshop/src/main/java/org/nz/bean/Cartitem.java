package org.nz.bean;

import java.math.BigDecimal;

public class Cartitem {
    private String cartItemId;

    private Integer quantity;

    private String bid;

    private String uid;

    private Integer orderBy;
    
    private Book book;
    
    private BigDecimal subtotal;
    
    
    public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId == null ? null : cartItemId.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

	@Override
	public String toString() {
		return "Cartitem [cartItemId=" + cartItemId + ", quantity=" + quantity + ", bid=" + bid + ", uid=" + uid
				+ ", orderBy=" + orderBy + ", book=" + book + ", subtotal=" + subtotal + "]";
	}

	
    
}