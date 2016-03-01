/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import java.math.BigDecimal;

/**
 * @author stefli
 * 
 */
public interface IWeighStrategy {

    public BigDecimal translate(byte[] bytes);

}
