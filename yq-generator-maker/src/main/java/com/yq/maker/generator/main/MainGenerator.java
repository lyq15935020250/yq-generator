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
public class MainGenerator extends GenerateTemplate{
    @Override
    protected void buildDist(String outputPath, String copySourcePath, String jarPath, String shellFilePath) {
        System.out.println("不生产dist文件");
    }
}
