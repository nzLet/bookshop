package org.nz.bean;

import java.util.List;

public class Category {
    private String cid;

    private String cname;

    private String pid;

    private String desc;

    private Integer orderBy;
    
    private List<Category> children;
    

    
	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", pid=" + pid + ", desc=" + desc + ", orderBy=" + orderBy
				+ ", children=" + children + "]";
	}

	
}