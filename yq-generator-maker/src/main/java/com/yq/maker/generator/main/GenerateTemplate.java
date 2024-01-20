package com.yq.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
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
 * @date 2023/12/8 17:21
 */
public abstract class GenerateTemplate {

    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMeta();

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 复制原始文件
        String copySourcePath = copySource(meta, outputPath);
        // 代码生成
        generateCode(meta, outputPath);
        // 构建jar包
        String jarPath = buildJar(outputPath, meta);
        // 封装脚本
        String shellFilePath = buildScript(outputPath, jarPath);
        // 生成精简版程序
        buildDist(outputPath, copySourcePath, jarPath, shellFilePath);
    }

    protected String buildDist(String outputPath, String copySourcePath, String jarPath, String shellFilePath) {
        String distOutPath = outputPath + "-dist";
        // -拷贝jar包
        String distJarAbsolutePath = distOutPath + File.separator + "target";
        FileUtil.mkdir(distJarAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, distJarAbsolutePath, true);
        // -拷贝脚本文件
        FileUtil.copy(shellFilePath, distOutPath, true);
        FileUtil.copy(shellFilePath + ".bat", distOutPath, true);
        // -拷贝源模板文件
        FileUtil.copy(copySourcePath, distOutPath, true);

        return distOutPath;
    }

    protected String buildZip(String outputPath){
        String zipPath = outputPath + ".zip";
        ZipUtil.zip(outputPath,zipPath);
        return zipPath;
    }

    protected String buildScript(String outputPath, String jarPath) {
        String shellFilePath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerate(shellFilePath, jarPath);
        return shellFilePath;
    }

    protected String buildJar(String outputPath, Meta meta) throws IOException, InterruptedException {
        JarGenerator.doGenerator(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        return jarPath;
    }

    protected void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
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

        // README.md
        inputFilePath = resourcesPath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + File.separator + "README.md";
        DynamicFileGenerator.doGenerator(inputFilePath, outputFilePath, meta);
    }

    protected String copySource(Meta meta, String outputPath) {
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String copySourcePath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, copySourcePath, false);
        return copySourcePath;
    }

}
