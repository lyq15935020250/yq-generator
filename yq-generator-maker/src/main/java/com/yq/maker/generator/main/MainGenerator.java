package com.yq.maker.generator.main;



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
