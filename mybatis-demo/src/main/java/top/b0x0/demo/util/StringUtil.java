package top.b0x0.demo.util;

/**
 * 字符串操作工具类
 * @author TANG
 *
 */
public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * @param s 需要判断的字符串
	 * @return 为空 返回 true  否则返回 false 
	 */
	public static boolean isEmpty(CharSequence s){
		if(null == s){
			return true;
		}
		if(s.length() == 0){
			return true;
		}
		if(s.equals("") || s.equals(" ")){
			return true;
		}
		if(s.equals("null")){
			return true;
		}
		return false;
	}
	
	
	
}
