package com.yq.maker.generator.main;



/**
 * @author lyq
 * @description:
 * @date 2023/12/5 16:58
 */
public class ZipGenerator extends GenerateTemplate{
    @Override
    protected String buildDist(String outputPath, String copySourcePath, String jarPath, String shellFilePath) {
        String distPath = super.buildDist(outputPath, copySourcePath, jarPath, shellFilePath);
        return super.buildZip(distPath);
    }
}
