package Tcp;

import file.Demo11;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class TcpEchoServer {

    private ServerSocket serverSocket = null;

    public TcpEchoServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("Server is running");
        ExecutorService service = Executors.newCachedThreadPool();
        while (true) {
            // build connection
            Socket clientSocket = serverSocket.accept();

            // demo1
//            processConnect(clientSocket);

            // demo2
//            Thread t = new Thread(() -> {
//                try {
//                    processConnect(clientSocket);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            t.start();
            //demo3
            service.submit(() -> {
                try {
                    processConnect(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }

    // 短链接和长连接
    public void processConnect(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] build connection\n",
                clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);
            //长连接写法
//            while (true) {
//                if (!scanner.hasNextLine()) {
//                    // 连接断开
//                    System.out.printf("[%s:%d] break connection\n",
//                            clientSocket.getInetAddress().toString(),
//                            clientSocket.getPort());
//                    break;
//                }
                // 1. read the request
//                String request = scanner.nextLine();
                // 2. process the request
                String response = process(scanner,printWriter);
                // 3.write response to client
//                printWriter.println(response);
//                // flush
//                printWriter.flush();
                System.out.printf("[%s:%d] request: ;response: %s\n",
                        clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),
                        response);
//            }
        } finally {
            clientSocket.close();
        }
    }

    public String process(Scanner scanner,PrintWriter printWriter) {
        Demo11 demo11 = new Demo11();
        demo11.start(scanner,printWriter);
        return "success";
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(8000);
        server.start();
    }

}
