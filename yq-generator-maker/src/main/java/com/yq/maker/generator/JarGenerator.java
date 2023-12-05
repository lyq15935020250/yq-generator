package com.yq.maker.generator;

import java.io.*;

/**
 * @author lyq
 * @description:
 * @date 2023/12/5 22:43
 */
public class JarGenerator {
    public static void doGenerator(String projectDir) throws IOException, InterruptedException {
        // 清理之前的构建并打包
        // 不同操作系统执行不同命令
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";

        String mvnCommand = winMavenCommand;
        ProcessBuilder processBuilder = new ProcessBuilder(mvnCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process start = processBuilder.start();

        // 读取命令的输出
        InputStream inputStream = start.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }

        // 等待完成命令
        int exitCode = start.waitFor();
        System.out.println("退出码：" + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerator("D:\\yq-generator\\yq-generator-maker\\generated\\acm-template-pro-generator");
    }
}
