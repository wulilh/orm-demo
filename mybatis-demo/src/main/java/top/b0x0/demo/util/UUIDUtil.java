package top.b0x0.demo.util;

import java.util.UUID;


/**
 * UUID操作工具类
 * @author TANG
 *
 */
public class UUIDUtil {
	/**
	 * 
	 * @return
	 */
    public static String  getUUID(){
    	String uuid = UUID.randomUUID().toString().replace("-", "");
    	return uuid;
    }
    
}
