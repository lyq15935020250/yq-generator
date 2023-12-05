package com.yq.maker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * @author lyq15935020250
 */
public class StaticFileGenerator {
    /**
     * 通过Hutool工具类复制文件
     * @param inputPath 输入文件路径
     * @param outputPath 输出文件路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

}
