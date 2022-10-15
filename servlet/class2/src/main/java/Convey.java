import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : servlet
 * @Package : PACKAGE_NAME
 * @createTime : 2022/10/15 14:29
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
class Msg {
    public String one;
    public String two;


    @Override
    public String toString() {
        return "Couple{" +
                "one='" + one + '\'' +
                ", two='" + two + '\'' +
                '}';
    }

//    public Msg(String one, String two) {
//        this.one = one;
//        this.two = two;
//    }
}

@WebServlet("/msg")
public class Convey extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        String body = readBody(req);
        Msg couple = objectMapper.readValue(req.getInputStream(), Msg.class);
        System.out.println(couple);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(couple.one + " say hello to " + couple.two);
    }


    private static String readBody(HttpServletRequest req) throws IOException {
        int contentLength = req.getContentLength();
        byte[] buffer = new byte[contentLength];
        InputStream inputStream = req.getInputStream();
        inputStream.read(buffer);
        return new String(buffer, StandardCharsets.UTF_8);
    }
}
