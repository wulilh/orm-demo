package com.shanhezai.mysql;

import java.util.UUID;

/**
 * .uuid工具类
 *
 * @author TANG
 * @date 2019/12/08
 */
public class UUIDUtils {
    /**
     * @return 生成32位的字符串
     */
    public static String getUuid() {
        return String.valueOf(UUID.randomUUID()).replace("-", "");
    }
}
