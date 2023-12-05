package ${basePackage}.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author lyq
 * @description: 核心生成器
 * @date 2023/11/13 22:21
 */
public class MainGenerator {

    public static void doGenerator(Object model) throws TemplateException, IOException {

        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;

        <#list fileConfig.files as fileInfo>
            inputPath = new File(inputRootPath,"${fileInfo.inputPath}").getAbsolutePath();
            outputPath = new File(outputRootPath,"${fileInfo.outputPath}").getAbsolutePath();
            <#if fileInfo.generateType == "dynamic">
            DynamicGenerator.doGenerator(inputPath, outputPath, model);
            <#else>
            StaticGenerator.copyFilesByHutool(inputPath, outputPath);
            </#if>
        </#list>

    }
}
