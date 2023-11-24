package com.yq.cli.pattern;

/**
 * @author lyqq
 * @description: 接受者
 * @date 2023/11/23 9:42
 */
public class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn(){
        System.out.println(name + "设备打开");
    }

    public void turnOff(){
        System.out.println(name + "设备关闭");
    }
}
