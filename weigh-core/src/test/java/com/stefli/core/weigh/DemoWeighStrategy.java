/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import java.math.BigDecimal;
import java.util.Random;

import com.stefli.core.weigh.IWeighStrategy;

/**
 * @author stefli
 * 
 */
public class DemoWeighStrategy implements IWeighStrategy {

    /**
     * Translate weigh bytes to result
     */
    @Override
    public BigDecimal translate(byte[] bytes) {
        BigDecimal weight = new BigDecimal(new Random().nextInt(10));
        return weight;
    }

}
