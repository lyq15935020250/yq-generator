package com.yq.cli.pattern;

/**
 * @author lyqq
 * @description: 调用者
 * @date 2023/11/23 10:03
 */
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton(){
        command.execute();
    }
}
