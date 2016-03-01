/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

/**
 * @author stefli
 * 
 */
public class WeighUtils {

    private WeighUtils() {
    }

    /**
     * Change byte to Hex
     * 
     * @param bytes
     * @return
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }

}
