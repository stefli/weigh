/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.TooManyListenersException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stefli
 * 
 */
public class SerialReader extends Observable implements Runnable, SerialPortEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SerialReader.class.getName());
    private DeviceConfig deviceConfig;
    private InputStream is;
    private SerialPort serialPort;
    private static String lastWeighString;

    public SerialReader(DeviceConfig deviceConfig) {
        this.deviceConfig = deviceConfig;
    }

    /**
     * Connect to the serial port and listen the data change
     * 
     * @throws WeighException
     */
    public void connect() throws WeighException {
        try {
            CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(this.deviceConfig
                    .getPortName());
            serialPort = (SerialPort) commPortIdentifier
                    .open(this.getClass().getName(), this.deviceConfig.getTimeout());
            is = serialPort.getInputStream();
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            serialPort.setSerialPortParams(this.deviceConfig.getRate(), this.deviceConfig.getDataBits(),
                    this.deviceConfig.getStopBits(), this.deviceConfig.getParity());
        } catch (NoSuchPortException e) {
            throw new WeighException("无效端口", e);
        } catch (PortInUseException e) {
            throw new WeighException("端口已经被占用", e);
        } catch (IOException e) {
            throw new WeighException("读取COM口数据异常", e);
        } catch (TooManyListenersException e) {
            throw new WeighException("端口监听过多", e);
        } catch (UnsupportedCommOperationException e) {
            throw new WeighException("无效的操作命令", e);
        }
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Disconnect the serial port
     */
    public void disconnect() {
        if (serialPort != null) {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("Can't close io stream.");
            }
            serialPort.close();
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        try {
            Thread.sleep(this.deviceConfig.getDelayRead());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        switch (event.getEventType()) {
        case SerialPortEvent.BI:
        case SerialPortEvent.OE:
        case SerialPortEvent.FE:
        case SerialPortEvent.PE:
        case SerialPortEvent.CD:
        case SerialPortEvent.CTS:
        case SerialPortEvent.DSR:
        case SerialPortEvent.RI:
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            break;
        case SerialPortEvent.DATA_AVAILABLE:
            try {
                int available = is.available();
                byte[] buffer = new byte[available];

                if (available > 0) {
                    is.read(buffer);
                    byte[] temp = new byte[available];
                    System.arraycopy(buffer, 0, temp, 0, available);
                    if (null == lastWeighString || !lastWeighString.equals(WeighUtils.bytesToHexString(temp))) {
                        lastWeighString = WeighUtils.bytesToHexString(temp);
                        setChanged();
                        notifyObservers(temp);
                    }
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            break;
        }
    }

}
