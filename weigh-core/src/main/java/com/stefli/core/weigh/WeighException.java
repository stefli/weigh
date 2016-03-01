/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

/**
 * @author stefli
 * 
 */
public class WeighException extends Exception {

    private static final long serialVersionUID = -5688751217860646454L;

    public WeighException() {
    }

    public WeighException(String message) {
        super(message);
    }

    public WeighException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WeighException(Throwable throwable) {
        super(throwable);
    }

}
