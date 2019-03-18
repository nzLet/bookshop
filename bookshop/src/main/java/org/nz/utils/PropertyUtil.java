package org.nz.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
* @author 作者 : YN
* @version 创建时间：2018年12月24日 下午2:59:36
* 类说明：
*
*/
public class PropertyUtil {

	private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private Properties props;
	private String fileName;
//    static{
//        loadProps();
//    }
    
    public PropertyUtil(String fileName) {
    	this.fileName = fileName;
	}

    private synchronized void loadProps(){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
//　　　　　	第一种，通过类加载器进行获取properties文件流"mail.properties"
//        	System.out.println("======"+PropertyUtil.class.getClassLoader().get);
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
//　　　　　 第二种，通过类进行获取properties文件流
//            in = PropertyUtil.class.getResourceAsStream("/"+fileName);
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error(fileName+"文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(fileName + "文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }

    public String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}

