package Udp.client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : JavaEE_primary
 * @Package : Udp
 * @createTime : 2022/8/11 12:54
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class UdpEchoClient {

    private DatagramSocket socket = null;

    public DatagramSocket getPacket() {
        return socket;
    }

    public UdpEchoClient() throws SocketException {
        socket = new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String request = sc.next();

            // set dest: ip, port.
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),
                    request.getBytes().length,
                    InetAddress.getByName("127.0.0.1"),
                    8000);

            // send message to server
            socket.send(requestPacket);

            // get response message
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],
                    4096);
            socket.receive(responsePacket);

            // convert message to string
            String response = new String(responsePacket.getData(),
                    0,
                    responsePacket.getLength());
            System.out.printf("[%s:%d] request=%s; response=%s\n",
                    requestPacket.getAddress().toString(),
                    requestPacket.getPort(),
                    request,
                    response);

        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client = new UdpEchoClient();
        client.start();
    }

}
