/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stefli
 * 
 */
public class WeighTest {

    private static final Logger logger = LoggerFactory.getLogger(WeighTest.class);

    public static void main(String[] args) {
        WeighClient client = null;
        try {
            DeviceConfig deviceConfig = new DeviceConfig("XX称重", "COM3", 2400);
            // IWeighStrategy weighStrategy = new DemoWeighStrategy();
            IWeighStrategy weighStrategy = new DefaultWeighStrategy();
            client = new WeighClient(deviceConfig, weighStrategy, new DemoWeighObserver());
            client.start();
        } catch (WeighException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (null != client) {
                    Thread.sleep(5000);
                    client.stop();
                }
            } catch (WeighException | InterruptedException e) {
            }
        }

    }

}
