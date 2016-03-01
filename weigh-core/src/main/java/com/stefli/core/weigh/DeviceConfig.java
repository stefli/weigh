/**
 * Copyright (c) 2016, Powered by stefli. All Rights Reserved. 
 */
package com.stefli.core.weigh;

import gnu.io.SerialPort;

/**
 * @author stefli
 * 
 */
public class DeviceConfig {

    // 设备名称
    private String name = "Default";
    // 端口名称
    private String portName = "COM3";
    // 波特率
    private int rate = 2400;
    // 设备读取间隔时间（毫秒）
    private int delayRead = 100;
    // 数据位
    private int dataBits = SerialPort.DATABITS_8;
    // 停止位
    private int stopBits = SerialPort.STOPBITS_1;
    // 无奇偶校验
    private int parity = SerialPort.PARITY_NONE;
    // 设备连接超时
    private int timeout = 4000;

    /**
     * @param name
     *            设备名称
     * @param portName
     *            端口名称
     * @param rate
     *            波特率
     */
    public DeviceConfig(String portName, int rate) {
        super();
        this.portName = portName;
        this.rate = rate;
    }

    /**
     * @param name
     *            设备名称
     * @param portName
     *            端口名称
     * @param rate
     *            波特率
     */
    public DeviceConfig(String name, String portName, int rate) {
        super();
        this.name = name;
        this.portName = portName;
        this.rate = rate;
    }

    /**
     * @param name
     *            设备名称
     * @param portName
     *            端口名称
     * @param rate
     *            波特率
     * @param delayRead
     *            设备读取间隔时间（毫秒）
     * @param dataBits
     *            数据位
     * @param stopBits
     *            停止位
     * @param parity
     *            无奇偶校验
     * @param timeout
     *            设备连接超时（毫秒）
     */
    public DeviceConfig(String name, String portName, int rate, int delayRead, int dataBits, int stopBits, int parity,
            int timeout) {
        super();
        this.name = name;
        this.portName = portName;
        this.rate = rate;
        this.delayRead = delayRead;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        this.timeout = timeout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getDelayRead() {
        return delayRead;
    }

    public void setDelayRead(int delayRead) {
        this.delayRead = delayRead;
    }

    public int getDataBits() {
        return dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public int getStopBits() {
        return stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "DeviceConfig [name=" + name + ", portName=" + portName + ", rate=" + rate + ", delayRead=" + delayRead
                + ", dataBits=" + dataBits + ", stopBits=" + stopBits + ", parity=" + parity + ", timeout=" + timeout
                + "]";
    }

}
