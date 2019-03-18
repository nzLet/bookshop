package org.nz.service;

import org.nz.bean.Admin;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月14日 上午9:48:10
* 类说明：
*
*/
public interface AdminService {

	Admin selectByName(String adminname);

}
