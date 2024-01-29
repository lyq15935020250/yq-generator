package ${basePackage}.cli.command;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import ${basePackage}.generator.MainGenerator;
import ${basePackage}.model.DataModel;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

/**
 * 读取 JSON 文件生成代码
 */
@Command(name = "json-generator", description = "读取 JSON 文件生成代码", mixinStandardHelpOptions = true)
@Data
public class JsonGeneratorCommand implements Callable<Integer> {

    @Option(names = {"-f", "--file"}, arity = "0..1", description = "JSON 文件路径", interactive = true, echo = true)
    private String filePath;


    @Override
    public Integer call() throws Exception {
        String jsonStr = FileUtil.readUtf8String(filePath);
        DataModel dataModel = JSONUtil.toBean(jsonStr, DataModel.class);
        MainGenerator.doGenerator(dataModel);
        return 0;
    }
}