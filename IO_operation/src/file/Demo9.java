package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : IO_operation
 * @Package : file
 * @createTime : 2022/8/8 16:38
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo9 {

    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("./src/files/change")) {
            Scanner sc = new Scanner(is, StandardCharsets.UTF_8);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
