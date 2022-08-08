package file;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : IO_operation
 * @Package : file
 * @createTime : 2022/8/8 14:55
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo7 {

    public static void main(String[] args) throws IOException {
        OutputStream os = new FileOutputStream("./src/files/change");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        PrintWriter pw = new PrintWriter(outputStreamWriter);
        pw.println("123");
        pw.print("frank");
        pw.print("second");
        pw.flush();
        pw.close();
        outputStreamWriter.close();
        os.close();
    }

}
