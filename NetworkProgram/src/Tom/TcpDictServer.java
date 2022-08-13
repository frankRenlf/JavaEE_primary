package Tom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TcpDictServer extends TcpEchoServer {
    private Map<String, String> dict = new HashMap<>();

    public TcpDictServer(int port) throws IOException {
        super(port);

        dict.put("cat", "小猫");
        dict.put("dog", "小狗");
        dict.put("fuck", "卧槽");
    }

    @Override
    public String process(String req) {
        return dict.getOrDefault(req, "俺也不知道是啥!");
    }

    public static void main(String[] args) throws IOException {
        TcpDictServer server = new TcpDictServer(8000);
        server.start();
    }
}
