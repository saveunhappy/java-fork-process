package com.github.hcsp.shell;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Fork {
    public static void main(String[] args) throws Exception {
        // 请在这里使用Java代码fork一个子进程，将fork的子进程的标准输出重定向到指定文件：工作目录下名为output.txt的文件
        // 工作目录是项目目录下的working-directory目录（可以用getWorkingDir()方法得到这个目录对应的File对象）
        // 传递的命令是sh run.sh
        // 环境变量是AAA=123
        // 1. 可执行程序 2. 参数
        ProcessBuilder pb = new ProcessBuilder("sh", "run.sh");
        // 3. 工作路径/工作目录
        pb.directory(getWorkingDir());
        // 4. 环境变量
        Map<String, String> envs = pb.environment();
        envs.put("AAA", "123");

        pb.redirectOutput(getOutputFile());

        pb.start().waitFor();
    }

    private static File getWorkingDir() {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        return projectDir.resolve("working-directory").toFile();
    }

    private static File getOutputFile() {
        return new File(getWorkingDir(), "output.txt");
    }
}
