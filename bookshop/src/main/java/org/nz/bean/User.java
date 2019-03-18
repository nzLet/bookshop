package org.nz.bean;

import java.util.Date;

public class User {
    private String uid;

    private String loginname;

    private String loginpass;

    private String email;

    private int status;

    private String activationCode;
    
    private String verifyCode;//验证码
    
    private String newpass;
    
    private String reloginpass;

    private String VALID_TIME;
    
    private Date retime;
    
    
	

	public Date getRetime() {
		return retime;
	}

	public void setRetime(Date retime) {
		this.retime = retime;
	}

	public String getVALID_TIME() {
		return VALID_TIME;
	}

	public void setVALID_TIME(String vALID_TIME) {
		VALID_TIME = vALID_TIME;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getReloginpass() {
		return reloginpass;
	}

	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass == null ? null : loginpass.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode == null ? null : activationCode.trim();
    }

	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname + ", loginpass=" + loginpass + ", email=" + email
				+ ", status=" + status + ", activationCode=" + activationCode + ", verifyCode=" + verifyCode
				+ ", newpass=" + newpass + ", reloginpass=" + reloginpass + ", VALID_TIME=" + VALID_TIME + ", retime="
				+ retime + "]";
	}

	
	
}