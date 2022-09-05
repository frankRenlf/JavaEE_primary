package Tcp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : JavaEE_primary
 * @Package : Tcp
 * @createTime : 2022/8/13 13:33
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class TcpEchoClient {

    private Socket socket = null;

    public TcpEchoClient(int port) throws IOException {
        this.socket = new Socket("127.0.0.1", port, null, 8080);
    }

    public void start() {
        Scanner order = new Scanner(System.in);
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            System.out.println("client is running");
            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);
            while (true) {
                // 1. read data form console
                System.out.print("> ");
                String sourcePath = order.nextLine();
                // 2. send request to server
                if (!check(sourcePath)) {
                    break;
                }
                System.out.print("> ");
                System.out.println(scanner.nextLine());
                System.out.print("> ");
                // 3. read response from server
                String dest = order.nextLine();

                printWriter.println(dest);
                printWriter.flush();

                transport(printWriter, sourcePath);
//                System.out.println(scanner.nextLine());
//                System.out.print("> ");
//                System.out.println(scanner.nextLine());
                // print result
                System.out.printf("request: %s; response: %s\n", sourcePath, dest);
                System.out.println(scanner.nextLine());
                System.out.println(scanner.nextLine());
//                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean check(String sourcePath) {
        if (sourcePath == null || sourcePath.equals("")) {
            System.out.println("源文路径不能为空。");
            // flush
            return false;
        }
        // 实例源文件
        File sourceFile = new File(sourcePath);
        // 校验合法性
        // 源文件不存在
        if (!sourceFile.exists()) {
            System.out.println("输入的源文件不存在，请检查。");
            // flush
            return false;
        }
        // 源路径对应的是一个目录
        if (sourceFile.isDirectory()) {
            System.out.println("输入的源文件是一个目录，请检查。");
            // flush
            return false;
        }
        return true;
    }

    private static void transport(PrintWriter printWriter, String sourcePath) {

        try (InputStream is = new FileInputStream(sourcePath)) {
            Scanner sc = new Scanner(is, StandardCharsets.UTF_8);
            while (sc.hasNextLine()) {
                printWriter.println(sc.nextLine());
            }
            printWriter.flush();
//            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient(8000);
        client.start();
    }


}
