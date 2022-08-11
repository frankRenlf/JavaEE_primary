package Tom;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

// 字典服务器/翻译服务器
// 希望实现一个 英译汉 的效果.
// 请求是一个英文单词, 响应是对应的中文翻译.
public class UdpDictServer extends UdpEchoServer{
    private Map<String, String> dict = new HashMap<>();

    public UdpDictServer(int port) throws SocketException {
        super(port);

        // 这里的数据可以无限的构造下去......
        // 即使是有道词典这种, 也是类似的方式实现 (打表)
        dict.put("cat", "小猫");
        dict.put("dog", "小狗");
        dict.put("fuck", "卧槽");
    }

    // 和 UdpEchoServer 相比, 只是 process 不同, 就重写这个方法即可!
    @Override
    public String process(String req) {
        return dict.getOrDefault(req, "这个词俺也不会!");
    }

    public static void main(String[] args) throws IOException {
        UdpDictServer server = new UdpDictServer(8000);
        server.start();
    }
}
