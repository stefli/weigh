/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stefli
 * 
 */
public class DefaultWeighStrategy implements IWeighStrategy {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWeighStrategy.class.getName());

    /**
     * Translate weigh bytes to result
     */
    @Override
    public BigDecimal translate(byte[] bytes) {
        BigDecimal weight = new BigDecimal(0);
        String weighString = WeighUtils.bytesToHexString((byte[]) bytes).toLowerCase();
        weight = new BigDecimal(process(weighString));
        if (logger.isDebugEnabled()) {
            logger.debug("Get weigh result-> " + weight + ", original->" + weighString);
        }
        return weight;
    }

    /**
     * Main process
     * 
     * @param weighString
     * @return
     */
    private String process(String weighString) {
        String[] str = new String[4];
        try {
            if (weighString.indexOf("ff") > -1) {
                int i = weighString.indexOf("ff");
                str[0] = weighString.substring(4 + i, 6 + i);
                str[1] = weighString.substring(6 + i, 8 + i);
                str[2] = weighString.substring(8 + i, 10 + i);
                str[3] = weighString.substring(10 + i, 12 + i);
                return str[3] + str[2] + str[1].substring(0, 1) + "." + str[1].substring(1, 2) + str[0];
            } else {
                return "0";
            }
        } catch (Exception e) {
            return "0";
        }
    }

}
