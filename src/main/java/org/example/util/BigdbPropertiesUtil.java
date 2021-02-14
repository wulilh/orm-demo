package org.example.util;

import org.example.bulkInsert.mysql.BulkInsert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author TANG
 * @description
 * @date 2021-02-14
 */
public class BigdbPropertiesUtil {

    private static final Logger log = LoggerFactory.getLogger(BigdbPropertiesUtil.class);

    private static final Properties BIGDB_PROPERTIES = new Properties();

    static {
        try {
            BIGDB_PROPERTIES.load(BulkInsert.class.getClassLoader().getResourceAsStream("bigdb.properties"));
            log.info("--------->> BIGDB_PROPERTIES : {}", BIGDB_PROPERTIES.toString());
        } catch (Exception e) {
            log.warn("Load Properties error : {}", e.getMessage());
        }
    }

    public static Properties getBigdbProperties() {
        return BIGDB_PROPERTIES;
    }
}
