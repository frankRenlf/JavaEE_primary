package file;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : IO_operation
 * @Package : file
 * @createTime : 2022/8/8 15:34
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo8 {

    private static void scanDir(File rootDir, String token, List<File> fileList) {
        File[] files = rootDir.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        // 开始查找
        for (File file : files) {
            if (file.isDirectory()) {
                // 如果是一个目录就递归查找子目录
                scanDir(file, token, fileList);
            } else {
                // 如果是符合条件的文件就记录
                System.out.println(file.getPath());
                if (file.getName().contains(token)) {
                    fileList.add(file);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner order = new Scanner(System.in);
        while (true) {
            System.out.print("Enter path: ");
            String path = order.nextLine();
            File filePath = new File(path);
            if (!filePath.isDirectory()) {
                System.out.println("Invalid path, try again");
                continue;
            }
            //
            System.out.print("Enter key string: ");
            String sub = order.nextLine();
            List<File> fileList = new ArrayList<>();
            scanDir(filePath, sub, fileList);
            //
            if (fileList.size() == 0) {
                System.out.println("Not found");
            }
            for (File file : fileList) {
                System.out.print("Delete " + file.getName() + " (y/n) : ");
                String dm = order.next();
                if (Objects.equals(dm, "y")) {
                    System.out.println(file.delete());
                }
            }
            System.out.print("0.Again or 1.exit: ");
            int key = order.nextInt();
            if (key == 0) {
                continue;
            } else if (key == 1) {
                break;
            } else {
                System.out.println("Invalid input, exit");
                break;
            }
        }
    }

}
