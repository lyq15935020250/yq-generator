package com.yq.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author lyqq
 * @description: 动态文件生成器
 * @date 2023/11/13 21:43
 */
public class DynamicFileGenerator {

    /**
     * 使用相对路径生成文件
     *
     * @param relativeInputPath  输入相对路径
     * @param outputPath 输出文件的路径
     * @param model      模型对象，用于传递给模板引擎的数据
     * @throws IOException       如果在读取文件或写入文件时发生 I/O 错误
     * @throws TemplateException 如果在处理模板时发生错误
     */
    public static void doGenerator(String relativeInputPath, String outputPath, Object model) throws IOException, TemplateException {
        // 创建 Configuration 对象，指定 FreeMarker 的版本
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        int lastIndexOf = relativeInputPath.lastIndexOf("/");
        String basePackagePath = relativeInputPath.substring(0,lastIndexOf);
        String templateName = relativeInputPath.substring(lastIndexOf + 1);

        // 指定模板文件所在的路径
        ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(DynamicFileGenerator.class, basePackagePath);
        configuration.setTemplateLoader(classTemplateLoader);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 获取 Template 对象
        Template template = configuration.getTemplate(templateName);
        // 文件不存在时创建文件和父目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }
        // 创建输出文件的 Writer 对象
        Writer out = new FileWriter(outputPath);
        // 使用模板处理模型对象并将结果写入输出文件
        template.process(model, out);
        // 关闭输出文件
        out.close();
    }

    /**
     * 使用 FreeMarker 模板引擎将指定的输入模板文件生成输出文件
     *
     * @param inputPath  输入模板文件的路径
     * @param outputPath 输出文件的路径
     * @param model      模型对象，用于传递给模板引擎的数据
     * @throws IOException       如果在读取文件或写入文件时发生 I/O 错误
     * @throws TemplateException 如果在处理模板时发生错误
     */
    public static void doGeneratorByPath(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // 创建 Configuration 对象，指定 FreeMarker 的版本
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 获取模板文件所在的路径
        File templateFilePath = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateFilePath);
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 获取模板文件的名称
        String templateName = new File(inputPath).getName();
        // 获取 Template 对象
        Template template = configuration.getTemplate(templateName);
        // 文件不存在时创建文件和父目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }
        // 创建输出文件的 Writer 对象
        Writer out = new FileWriter(outputPath);
        // 使用模板处理模型对象并将结果写入输出文件
        template.process(model, out);
        // 关闭输出文件
        out.close();
    }

}
