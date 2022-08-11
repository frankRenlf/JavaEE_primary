package Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : JavaEE_primary
 * @Package : Udp
 * @createTime : 2022/8/11 12:43
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class UdpEchoServer {

    private DatagramSocket socket = null;

    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void start() throws IOException {
        System.out.println("UDP Echo Server start");
        while (true) {
            // read request from client
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(requestPacket);
            // receive request and explain it, then turn the datagram packet to string
            String request = new String(requestPacket.getData(), 0, requestPacket.getLength());
            // process it
            String response = process(request);
            // construct datagram packet object
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.length(), requestPacket.getSocketAddress());

            socket.send(responsePacket);
            System.out.printf("[%s:%d] request=%s; response=%s\n", requestPacket.getAddress().toString(), requestPacket.getPort(), request, response);
            System.out.printf("requestPacket=%s; responsePacket=%s\n", requestPacket, responsePacket);
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        // start server, port 0-65535, system contains 0-1024,
        UdpEchoServer server = new UdpEchoServer(8000);
        server.start();
    }

}
