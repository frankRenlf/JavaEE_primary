package file;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : IO_operation
 * @Package : File
 * @createTime : 2022/8/7 14:13
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
import java.io.IOException;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        // 前面没写 ./ , 也相当于是 ./, ./ 可以省略~~
        File file = new File("C:\\Users\\11195\\IdeaProjects\\JavaEE_primary\\IO_operation\\src\\Files\\test.txt");
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println("========================");
        // 创建文件
        file.createNewFile();
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
    }
}
