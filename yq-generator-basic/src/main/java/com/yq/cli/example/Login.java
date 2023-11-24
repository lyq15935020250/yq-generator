package com.yq.cli.example;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

/**
 * @author lyqq
 * @description: TestPicocli
 * @date 2023/11/22 21:20
 */
public class Login implements Callable<Integer> {
    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u","user123","-p","123","456");
    }

    @Option(names = {"-u","--user"},description = "User Name")
    String user;

    @Option(names = {"-p","--password"},arity = "0..1",description = "Passphrase",interactive = true)
    String password;

    @Option(names = {"-cp","--checkPassword"},arity = "0..1",description = "Check PassWord",interactive = true)
    String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println("PassWord = " + password);
        System.out.println("CheckPassWord = " + checkPassword);
        return 0;
    }
}
