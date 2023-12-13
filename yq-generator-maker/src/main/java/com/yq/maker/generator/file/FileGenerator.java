package com.yq.maker.generator.file;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author lyqq
 * @description: 核心生成器
 * @date 2023/11/13 22:21
 */
public class FileGenerator {

    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
//        整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "yq-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
//        生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
//        生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/yq/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerator(inputDynamicFilePath, outputDynamicFilePath, model);
    }
}
