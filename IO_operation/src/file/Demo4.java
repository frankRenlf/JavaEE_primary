package file;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : IO_operation
 * @Package : file
 * @createTime : 2022/8/7 14:19
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo4 {

    public static void main(String[] args) {
        File file = new File("./src/file123");
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        System.out.println(file.getPath());
        System.out.println(Arrays.toString(file.list()));
//        file.mkdirs();
//        System.out.println(file.exists());
//        System.out.println(file.isDirectory());
    }

}
