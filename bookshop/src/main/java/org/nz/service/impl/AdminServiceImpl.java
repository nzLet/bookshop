package org.nz.service.impl;

import org.nz.bean.Admin;
import org.nz.dao.AdminMapper;
import org.nz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月14日 上午9:54:40
* 类说明：
*
*/
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public Admin selectByName(String adminname) {
		
		Admin admin = adminMapper.selectByName(adminname);
		
		return admin;
	}

}
