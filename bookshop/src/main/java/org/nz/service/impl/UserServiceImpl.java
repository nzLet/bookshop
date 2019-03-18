package org.nz.service.impl;

import java.util.Date;

import org.nz.bean.User;
import org.nz.dao.UserMapper;
import org.nz.service.UserService;
import org.nz.utils.CommonUtils;
import org.nz.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;



/**
 * @author 作者 : YN
 * @version 创建时间：2019年1月29日 下午8:30:30 类说明：
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByName(String loginname) {

		return userMapper.findByName(loginname);
	}

	@Override
	public boolean updateUser(String newpass) {
		User user = new User();
		user.setLoginpass(newpass);
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	/**
	 * 是否有这个用户名，如果有，看状态
	 */
	@Override
	public User findByLoginName(String loginname) {

		User user = userMapper.findByLoginName(loginname);
		System.out.println(user);
		String uid=user.getUid();
		if (!ObjectUtils.isEmpty(user) && user.getStatus() == 0) {
			System.out.println(user);
			if (userMapper.deleteByPrimaryKey(uid) > 0) {
				return null;
			}
			return user;
		}
		return null;

	}

	@Override
	public User findByEmail(String email) {
		User user = userMapper.findByEmail(email);
		String uid=user.getUid();
		if (!ObjectUtils.isEmpty(user) && user.getStatus() == 0) {
			System.out.println(user);
			if (userMapper.deleteByPrimaryKey(uid) > 0) {
				return null;
			}
			return user;
		}
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		user.setStatus(0);
		user.setRetime(new Date());
		user.setUid(CommonUtils.uuid());
		user.setActivationCode(CommonUtils.uuid());
		System.out.println(user);
		boolean flag = false;
		try{
			flag = userMapper.insert(user)>0;
			if(flag) {
				new Thread(new MailUtil(user)).start();
				return true;
			}
			
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean updateByActivationCode(String activationCode, String VALID_TIME) {
		User user = new User();
		user.setActivationCode(activationCode);
		user.setVALID_TIME(VALID_TIME);
		user.setStatus(1);
		return userMapper.updateByActivationCode(user) > 0;
	}

}
