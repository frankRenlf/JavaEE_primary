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
 * @createTime : 2022/8/7 14:21
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo5 {

    public static void main(String[] args) throws IOException {
        File file = new File("./src/files/test.txt");
        file.createNewFile();
        System.out.println(file.renameTo(new File("./src/files/change")));
    }

}
