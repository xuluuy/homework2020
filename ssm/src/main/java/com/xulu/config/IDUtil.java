package com.xulu.config;

import java.util.UUID;

/**
 * @author xuluuy
 * @create 2020-01-29 9:58
 */
public class IDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
