package Udp.server;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : JavaEE_primary
 * @Package : Udp.server
 * @createTime : 2022/8/11 14:48
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class UdpDictServer extends UdpEchoServer {

    private Map<String, String> dict = new HashMap<>();

    public UdpDictServer(int port) throws SocketException {
        super(port);
        dict.put("hello", "你好");
        dict.put("welcome", "欢迎");
    }

    @Override
    public String process(String request) {
        return dict.getOrDefault(request,"我也不会");
    }

    public static void main(String[] args) throws IOException {
        UdpDictServer server = new UdpDictServer(8000);
        server.start();
    }

}
