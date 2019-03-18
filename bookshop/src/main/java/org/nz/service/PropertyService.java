package org.nz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
* @author 作者 : YN
* @version 创建时间：2019年2月13日 下午4:16:52
* 类说明：
*
*/
@Service
public class PropertyService {
	@Value("${email.active.valid.time}")
	public String VALID_TIME;
}
