package com.yq.maker;

import com.yq.maker.generator.main.GenerateTemplate;
import com.yq.maker.generator.main.MainGenerator;
import com.yq.maker.generator.main.ZipGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author lyqq
 * @description: 全局调用入口，接受用户的参数，创建命令执行器并调用执行
 * @date 2023/11/23 20:54
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
//        GenerateTemplate generateTemplate = new MainGenerator();
        GenerateTemplate generateTemplate = new ZipGenerator();
        generateTemplate.doGenerate();
    }
}
