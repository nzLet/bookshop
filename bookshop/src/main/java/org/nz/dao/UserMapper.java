package org.nz.dao;

import org.nz.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User findByName(String loginname);

	User findByLoginName(String loginname);

	User findByEmail(String email);

	int updateByActivationCode(User user);
}