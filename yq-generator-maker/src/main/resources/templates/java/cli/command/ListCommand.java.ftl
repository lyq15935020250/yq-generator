package ${basePackage}.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * @author lyq
 * @description: 查看要生成的原始文件列表信息
 * @date 2023/11/23 15:23
 */
@Command(name = "list",description = "查看文件路径",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{

    @Override
    public void run() {
        String inputPath = "${fileConfig.inputRootPath}";
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file:files) {
            System.out.println(file);
        }
    }
}
