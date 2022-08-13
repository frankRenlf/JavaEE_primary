package Tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
    public void processConnect(Socket clientSocket) {
        
    }

}
