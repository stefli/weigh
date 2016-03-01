/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stefli
 * 
 */
public class DemoWeighObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(DemoWeighObserver.class);

    @Override
    public void update(Observable o, Object arg) {
        logger.info("Get result->" + (BigDecimal) arg);
    }

}
