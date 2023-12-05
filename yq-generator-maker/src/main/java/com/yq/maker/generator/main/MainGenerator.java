package com.yq.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.yq.maker.generator.JarGenerator;
import com.yq.maker.generator.ScriptGenerator;
import com.yq.maker.generator.file.DynamicFileGenerator;
import com.yq.maker.meta.Meta;
import com.yq.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author lyq
 * @description:
 * @date 2023/12/5 16:58
 */
public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMeta();

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 读取 rescources 目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String resourcesPath = classPathResource.getAbsolutePath();

        // Java 包基础路径
        String basePackage = meta.getBasePackage();
        String basePackagePath = StrUtil.join("/", StrUtil.split(basePackage, "."));
        String baseJavaPackagePath = outputPath + File.separator + "src/main/java/" + basePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DateModel
        inputFilePath = resourcesPath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = baseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // cli.command.GeneratorCommand
        inputFilePath = resourcesPath + File.separator + "templates/java/cli/command/GeneratorCommand.java.ftl";
        outputFilePath = baseJavaPackagePath + "/cli/command/GeneratorCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = resourcesPath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = baseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = resourcesPath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = baseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = resourcesPath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = baseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // Main
        inputFilePath = resourcesPath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = baseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // generator.DynamicGenerator
        inputFilePath = resourcesPath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = baseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = resourcesPath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = baseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // generator.StaticGenerator
        inputFilePath = resourcesPath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = baseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        // pom.xml
        inputFilePath = resourcesPath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);

        JarGenerator.doGenerator(outputPath);

        // 封装脚本
        String shellFilePath = outputPath + File.separator + "generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellFilePath, jarPath);
    }
}
