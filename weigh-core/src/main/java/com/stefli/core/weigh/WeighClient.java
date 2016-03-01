/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stefli
 * 
 */
public class WeighClient {

    private static final Logger logger = LoggerFactory.getLogger(WeighClient.class);

    private DeviceConfig deviceConfig;
    private SerialReader reader;
    private WeighObserver observer;

    public WeighClient(DeviceConfig deviceConfig, Observer resultObserver) {
        this(deviceConfig, new DefaultWeighStrategy(), resultObserver);
    }

    public WeighClient(DeviceConfig deviceConfig, IWeighStrategy weighStrategy, Observer resultObserver) {
        this.deviceConfig = deviceConfig;
        this.observer = new WeighObserver(weighStrategy);
        this.observer.addObserver(resultObserver);
    }

    /**
     * Start
     * 
     * @throws WeighException
     */
    public void start() throws WeighException {
        logger.info("Start to read weight from weighing equipment.");
        this.reader = new SerialReader(this.deviceConfig);
        this.reader.addObserver(observer);
        this.reader.connect();
    }

    /**
     * Stop
     * 
     * @throws WeighException
     */
    public void stop() throws WeighException {
        this.reader.disconnect();
    }

}
