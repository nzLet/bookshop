package org.nz.utils;

import java.util.UUID;



/**
* @author 作者 : YN
* @version 创建时间：2019年2月11日 下午1:09:47
* 类说明：
*
*/
public class CommonUtils {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

}
