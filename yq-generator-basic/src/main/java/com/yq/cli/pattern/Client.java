package com.yq.cli.pattern;

/**
 * @author lyqq
 * @description: 客户端
 * @date 2023/11/23 10:07
 */
public class Client {
    public static void main(String[] args) {
//        创建接受者对象
        Device tv = new Device("TV");
        Device stereo = new Device("Stereo");
//        创建具体命令，与接受者对象绑定
        TurnOnCommand turnOnCommand = new TurnOnCommand(tv);
        TurnOffCommand turnOffCommand = new TurnOffCommand(stereo);
//        创建调用者对象
        RemoteControl remoteControl = new RemoteControl();
//        执行命令
        remoteControl.setCommand(turnOnCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOffCommand);
        remoteControl.pressButton();
    }
}
