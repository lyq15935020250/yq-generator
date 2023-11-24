package com.yq.cli.pattern;

/**
 * @author lyqq
 * @description: 关闭命令
 * @date 2023/11/23 9:41
 */
public class TurnOnCommand implements Command{

    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }
}
