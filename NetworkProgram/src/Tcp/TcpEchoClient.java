package Tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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
        this.socket = new Socket("127.0.0.1", port);
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
                String request = order.next();
                // 2. send request to server
                printWriter.println(request);
                printWriter.flush();
                // 3. read response from server
                String response = scanner.next();
                // print result
                System.out.printf("request: %s; response: %s\n", request, response);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient(8000);
        client.start();
    }


}
