package com.yq.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author lyq15935020250
 */
public class StaticGenerator {
    public static void main(String[] args) {
//        获取项目根路径
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
//        输入路径：ACM示例代码模板目录
        String inputPath = new File(parentFile,"yq-generator-demo-projects/acm-template").getAbsolutePath();
//        输出路径：项目根路径
        String outputPath = projectPath;
        copyFilesByHutool(inputPath,outputPath);
    }



    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void copyFilesByRecursive(String inputPath,String outputPath){
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile,outputFile);
        } catch (Exception e) {
            System.out.println("文件复制失败");
            e.printStackTrace();
        }

    }

    /**
     * 文件 A => 目录 B，则文件 A 放在目录 B 下
     * 文件 A => 文件 B，则文件 A 覆盖文件 B
     * 目录 A => 目录 B，则目录 A 放在目录 B 下
     *
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制
     */
    private static void copyFileByRecursive(File inputFile,File outputFile) throws IOException {
//        区分目录还是文件
        if (inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            File destOutPutFile = new File(outputFile, inputFile.getName());
//            如果是目录，就先创建目标目录
            if (!destOutPutFile.exists()){
                destOutPutFile.mkdirs();
            }
//            获取目录下的所有文件和子目录
            File[] listFiles = inputFile.listFiles();
//            无子文件，直接结束
            if (ArrayUtil.isEmpty(listFiles)){
                return;
            }
            for (File file: listFiles) {
//                递归下一层文件
                copyFileByRecursive(file,destOutPutFile);
            }
        }else {
//            是文件直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
