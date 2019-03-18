package org.nz.controller.front;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nz.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 作者 : YN
* @version 创建时间：2019年1月30日 上午10:19:35
* 类说明：
* 
*/
@Controller
@RequestMapping("/get")
public class VcodeController {
	/**
	 * 获取验证码
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/code")
	public void vcode(HttpServletResponse response,HttpServletRequest request) throws IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();

    	System.out.println(vc.getText());
    	
		request.getSession().setAttribute("vCode", vc.getText());
		
		VerifyCode.output(image, response.getOutputStream());

		
		
	}
	
}
