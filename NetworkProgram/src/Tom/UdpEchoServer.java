package Tom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpEchoServer {
    // 要想创建 UDP 服务器, 首先要先打开一个 socket 文件.
    private DatagramSocket socket = null;

    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    // 启动服务器
    public void start() throws IOException {
        System.out.println("服务器启动!");
        while (true) {
            // 1. 读取客户端发来的请求.
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(requestPacket);
            // 2. 对请求进行解析, 把 DatagramPacket 转成一个 String
            String request = new String(requestPacket.getData(), 0, requestPacket.getLength());
            // 3. 根据请求, 处理响应. 虽然咱们这是个回显服务器. 但是还是可以单独搞个方法来做这个事情.
            String response = process(request);
            // 4. 把响应构造成 DatagramPacket 对象.
            //    构造响应对象, 要搞清楚, 对象要发给谁!! 谁给咱们发的请求, 就把响应发给谁~~~
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length, requestPacket.getSocketAddress());
            // 5. 把这个 DatagramPacket 对象返回给客户端.
            socket.send(responsePacket);
            System.out.printf("[%s:%d] req=%s; resp=%s\n", requestPacket.getAddress().toString(), requestPacket.getPort(),
                    request, response);
        }
    }

    // 通过这个方法, 实现根据请求计算响应 这个过程.
    // 由于是回显服务器, 所以不涉及到其他逻辑.
    // 但是如果是其他服务器, 就可以在 process 里面, 来加上一些其他逻辑的处理.
    public String process(String req) {
        return req;
    }

    public static void main(String[] args) throws IOException {
        // 真正启动服务器, 这个端口号说是随便写, 但是也有范围的. 0 -> 65535
        // 但是一般来说 1024 以下的端口, 都是系统保留.
        // 因此咱们自己写代码, 端口尽量还是选择 1024 以上, 65535 以下的.
        UdpEchoServer server = new UdpEchoServer(8000);
        server.start();
    }
}
