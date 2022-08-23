package file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : IO_operation
 * @Package : file
 * @createTime : 2022/8/8 16:54
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo11 {


    public void start(Scanner scanner, PrintWriter printWriter) {
        // 接收源文件目录

//        printWriter.println("请输入源文件的路径：");
//        printWriter.flush();
        String sourcePath = scanner.nextLine();
        if (sourcePath == null || sourcePath.equals("")) {

            printWriter.println("源文路径不能为空。");
            // flush
            printWriter.flush();
            return;
        }
        // 实例源文件
        File sourceFile = new File(sourcePath);
        // 校验合法性
        // 源文件不存在
        if (!sourceFile.exists()) {
            printWriter.println("输入的源文件不存在，请检查。");
            // flush
            printWriter.flush();
            return;
        }
        // 源路径对应的是一个目录
        if (sourceFile.isDirectory()) {
            printWriter.println("输入的源文件是一个目录，请检查。");
            // flush
            printWriter.flush();
            return;
        }

        // 输入目标路径
        printWriter.println("请输入目标路径：");
        // flush
        printWriter.flush();
        String destPath = scanner.nextLine();
        if (destPath == null || destPath.equals("")) {
            printWriter.println("目标路径不能为空。");
            // flush
            printWriter.flush();
            return;
        }
        File destFile = new File(destPath);
        // 检查目标路径合法性
        // 已存在
        if (destFile.exists()) {
            // 是一个目录
            if (destFile.isDirectory()) {
                printWriter.println("输入的目标路径是一个目录，请检查。");
                // flush
                printWriter.flush();
            }
            // 是一个文件
            if (destFile.isFile()) {
                printWriter.println("文件已存在，是否覆盖，y/n?");
                // flush
                printWriter.flush();
                String input = scanner.nextLine();
                if (input != null && input.toLowerCase().equals("n")) {
                    printWriter.println("停止复制。");
                    // flush
                    printWriter.flush();
                    return;
                }
            }
        }

        copyFile(sourceFile, destFile);
        printWriter.println("copy success");
        // flush
        printWriter.flush();

    }

    private static void copyFile(File src, File dest) {
        try (InputStream is = new FileInputStream(src)) {
            Scanner sc = new Scanner(is, StandardCharsets.UTF_8);
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(dest), true, StandardCharsets.UTF_8)) {
                while (sc.hasNextLine()) {
                    pw.println(sc.nextLine());
                }
//                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
