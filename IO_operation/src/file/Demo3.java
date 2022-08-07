package file;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : IO_operation
 * @Package : file
 * @createTime : 2022/8/7 14:16
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo3 {

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("C:\\Users\\11195\\IdeaProjects\\JavaEE_primary\\IO_operation\\src\\Files\\test2.txt");
        file.createNewFile();
        System.out.println(file.exists());
        Thread.sleep(1000);
        file.delete();
        System.out.println(file.exists());
    }
}
