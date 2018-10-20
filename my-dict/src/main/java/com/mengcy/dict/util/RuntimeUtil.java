package com.mengcy.dict.util;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class RuntimeUtil {

    public static long getUsedMemory(){
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}
