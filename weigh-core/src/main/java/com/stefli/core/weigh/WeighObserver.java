/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

/**
 * @author stefli
 * 
 */
public class WeighObserver extends Observable implements Observer {

    private IWeighStrategy weighStrategy;

    public WeighObserver(IWeighStrategy weighStrategy) {
        this.weighStrategy = weighStrategy;
    }

    public void update(Observable o, Object arg) {
        BigDecimal weight = this.weighStrategy.translate((byte[]) arg);
        setChanged();
        notifyObservers(weight);
    }
}
