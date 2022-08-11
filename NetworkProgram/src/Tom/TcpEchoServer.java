package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpEchoServer {
    private ServerSocket serverSocket = null;

    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动!");
        ExecutorService service = Executors.newCachedThreadPool();
        while (true) {
            // 如果当前没有客户端来建立连接, 就会阻塞等待!!
            Socket clientSocket = serverSocket.accept();
            // 不再直接调用了, 而是创建一个新的线程, 让新的线程来调用.
            // [版本1] 单线程版本, 存在 bug, 无法处理多个客户端.
            // processConnect(clientSocket);

            // [版本2] 多线程版本. 主线程负责拉客, 新线程负责通信
            // 虽然比版本1 有提升了, 但是涉及到频繁创建销毁线程, 在高并发的情况下, 负担比较重的.
//            Thread t = new Thread(() -> {
//                try {
//                    processConnect(clientSocket);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            t.start();

            // [版本3] 使用线程池, 来解决频繁创建销毁线程的问题.
            // 此处不太适合使用 "固定个数的"
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        processConnect(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    // 通过这个方法, 给当前连上的这个客户端, 提供服务!!
    // 一个连接过来了, 服务方式可能有两种:
    // 1. 一个连接只进行一次数据交互 (一个请求+一个响应)   短连接
    // 2. 一个连接进行多次数据交互 (N 个请求 + N 个响应)   长连接
    // 此处来写长连接的版本
    public void processConnect(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 建立连接!\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);

            // 这里是长连接的写法, 需要通过 循环 来获取到多次交互情况.
            while (true) {
                if (!scanner.hasNext()) {
                    // 连接断开. 当客户端断开连接的时候, 此时 hasNext 就会返回 false
                    System.out.printf("[%s:%d] 断开连接!\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
                    break;
                }
                // 1. 读取请求并解析
                String request = scanner.next();
                // 2. 根据请求计算响应
                String response = process(request);
                // 3. 把响应写回给客户端
                printWriter.println(response);
                //    刷新一下缓冲区, 避免数据没有真的发出去~
                printWriter.flush();
                System.out.printf("[%s:%d] req: %s; resp: %s\n",
                        clientSocket.getInetAddress().toString(), clientSocket.getPort(),
                        request, response);
            }
        } finally {
            // 加在这里是更稳妥的做法!!
            clientSocket.close();
        }
    }

    public String process(String req) {
        return req;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(8000);
        server.start();
    }
}
