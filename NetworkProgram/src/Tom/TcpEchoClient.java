package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    private Socket socket = null;

    public TcpEchoClient() throws IOException {
        // new 这个对象, 需要和服务器建立连接的!!
        // 建立连接, 就得知道服务器在哪里!!
        socket = new Socket("127.0.0.1", 8000);
    }

    public void start() throws IOException {
        // 由于实现的是长连接, 一个连接会处理 N 个请求和响应
        Scanner scanner = new Scanner(System.in);
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            Scanner scannerNet = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);
            while (true) {
                // 1. 从控制台读取用户的输入.
                System.out.print("> ");
                String request = scanner.next();
                // 2. 把请求发送给服务器
                printWriter.println(request);
                printWriter.flush();
                // 3. 从服务器读取响应
                String response = scannerNet.next();
                // 4. 把结果显示到界面上
                System.out.printf("req: %s; resp: %s\n", request, response);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient();
        client.start();
    }
}
