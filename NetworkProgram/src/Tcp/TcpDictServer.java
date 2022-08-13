package Tcp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : JavaEE_primary
 * @Package : Tcp
 * @createTime : 2022/8/13 16:13
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class TcpDictServer extends TcpEchoServer {
    public TcpDictServer(int port) throws IOException {
        super(port);
        map = new HashMap<>();
        map.put("frank", "wjq");
        map.put("lily", "qwer");
    }

    private Map<String, String> map = null;

    @Override
    public String process(String request) {
        return map.getOrDefault(request, "Not found");
    }

    public static void main(String[] args) throws IOException {
        TcpDictServer server = new TcpDictServer(8000);
        server.start();
    }

}
