package com.yq.generator;

import com.yq.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author lyqq
 * @description: 核心生成器
 * @date 2023/11/13 22:21
 */
public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("lyq");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果:");
        doGenerator(mainTemplateConfig);
    }

    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
//        整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "yq-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
//        生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
//        生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "acm-template/src/com/yq/acm/MainTemplate.java";
        DynamicGenerator.doGenerator(inputDynamicFilePath, outputDynamicFilePath, model);
    }
}
