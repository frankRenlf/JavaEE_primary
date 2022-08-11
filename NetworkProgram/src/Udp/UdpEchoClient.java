package Udp;

import java.net.DatagramPacket;

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

    private DatagramPacket packet = null;

    public DatagramPacket getPacket() {
        return packet;
    }

    public UdpEchoClient() {
//        this.packet = new DatagramPacket();
    }
}
