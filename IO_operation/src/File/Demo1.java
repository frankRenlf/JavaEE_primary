package File;

import java.io.File;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : IO_operation
 * @Package : File
 * @createTime : 2022/8/7 14:07
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo1 {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\11195\\IdeaProjects\\JavaEE_primary\\IO_operation\\src\\Files\\test.txt");
        System.out.println(Arrays.toString(file.list()));
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());


    }

}
