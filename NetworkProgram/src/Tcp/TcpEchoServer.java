package Tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
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
public class TcpEchoServer {

    private ServerSocket serverSocket = null;

    public TcpEchoServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("Server is running");
        while (true) {
            // build connection
            Socket clientSocket = serverSocket.accept();
            processConnect(clientSocket);
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
            while (true) {
                if (!scanner.hasNext()) {
                    // 连接断开
                    System.out.printf("[%s:%d] break connection\n",
                            clientSocket.getInetAddress().toString(),
                            clientSocket.getPort());
                    break;
                }
                // 1. read the request
                String request = scanner.next();
                // 2. process the request
                String response = process(request);
                // 3.write response to client
                printWriter.println(response);
                // flush
                printWriter.flush();
                System.out.printf("[%s:%d] request: %s;response: %s connection\n",
                        clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),
                        request,
                        response);
            }
        } finally {
            clientSocket.close();
        }
    }

    private String process(String request) {
        return "Hi " + request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(8000);
        server.start();
    }

}
