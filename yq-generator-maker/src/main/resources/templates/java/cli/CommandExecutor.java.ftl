package ${basePackage}.cli;

import ${basePackage}.cli.command.ConfigCommand;
import ${basePackage}.cli.command.GeneratorCommand;
import ${basePackage}.cli.command.JsonGeneratorCommand;
import ${basePackage}.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * @author lyq
 * @description: 绑定所有子命令，提供执行命令的方法
 * @date 2023/11/23 15:26
 */
@Command(name = "${name}",mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GeneratorCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand())
                .addSubcommand(new JsonGeneratorCommand());
    }

    @Override
    public void run() {
//        不输入子命令时，给出友好提示
        System.out.println("请输入具体命令，或输入 --help 查看命令提示");
    }

    public Integer doExecutor(String[] args){
        return commandLine.execute(args);
    }
}
