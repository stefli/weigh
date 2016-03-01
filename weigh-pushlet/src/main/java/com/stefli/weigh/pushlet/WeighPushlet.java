/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.weigh.pushlet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefli.core.weigh.DeviceConfig;
import com.stefli.core.weigh.WeighClient;
import com.stefli.core.weigh.WeighException;

/**
 * @author stefli
 * 
 */
public class WeighPushlet extends EventPullSource implements Observer, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WeighPushlet.class);
    private static final long serialVersionUID = -8719012875134554315L;
    private WeighClient client;
    private Event event;

    public WeighPushlet() {
        try {
            event = Event.createDataEvent("/demo/data");
            DeviceConfig deviceConfig = new DeviceConfig("XX称重", "COM3", 2400);
            client = new WeighClient(deviceConfig, this);
            client.start();
        } catch (WeighException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected long getSleepTime() {
        return 500;
    }

    @Override
    protected Event pullEvent() {
        return event;
    }

    public void update(Observable o, Object arg) {
        event.setField("data", ((BigDecimal) arg).toString());
    }

}
