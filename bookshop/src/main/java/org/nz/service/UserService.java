package org.nz.service;

import org.nz.bean.User;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月29日 下午8:26:48
* 类说明：
*
*/
public interface UserService {

	User findByName(String loginname);

	boolean updateUser(String newpass);

	User findByLoginName(String loginname);

	User findByEmail(String email);

	boolean insertUser(User user);

	boolean updateByActivationCode(String activationCode, String VALID_TIME);
	

}
