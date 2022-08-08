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


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 接收源文件目录
        System.out.println("请输入源文件的路径：");
        String sourcePath = scanner.next();
        if (sourcePath == null || sourcePath.equals("")) {
            System.out.println("源文路径不能为空。");
            return;
        }
        // 实例源文件
        File sourceFile = new File(sourcePath);
        // 校验合法性
        // 源文件不存在
        if (!sourceFile.exists()) {
            System.out.println("输入的源文件不存在，请检查。");
            return;
        }
        // 源路径对应的是一个目录
        if (sourceFile.isDirectory()) {
            System.out.println("输入的源文件是一个目录，请检查。");
            return;
        }

        // 输入目标路径
        System.out.println("请输入目标路径：");
        String destPath = scanner.next();
        if (destPath == null || destPath.equals("")) {
            System.out.println("目标路径不能为空。");
            return;
        }
        File destFile = new File(destPath);
        // 检查目标路径合法性
        // 已存在
        if (destFile.exists()) {
            // 是一个目录
            if (destFile.isDirectory()) {
                System.out.println("输入的目标路径是一个目录，请检查。");
            }
            // 是一个文件
            if (destFile.isFile()) {
                System.out.println("文件已存在，是否覆盖，y/n?");
                String input = scanner.next();
                if (input != null && input.toLowerCase().equals("")) {
                    System.out.println("停止复制。");
                    return;
                }
            }
        }

        copyFile(sourceFile, destFile);

    }

    private static void copyFile(File src, File dest) {
        try (InputStream is = new FileInputStream(src)) {
            Scanner sc = new Scanner(is, StandardCharsets.UTF_8);
            try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dest), StandardCharsets.UTF_8))) {
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
