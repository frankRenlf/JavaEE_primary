import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : servlet
 * @Package : PACKAGE_NAME
 * @createTime : 2022/10/10 15:38
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */

class User {
    public int id;
    public String name;
    public int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

@WebServlet("/hello")
public class TestHello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        StringBuilder respBody = new StringBuilder();
        respBody.append(req.getProtocol());
        respBody.append("<br>");
        respBody.append(req.getMethod());
        respBody.append("<br>");
        respBody.append(req.getRequestURI());
        respBody.append("<br>");
        respBody.append("resp ContentType: ").append(resp.getContentType());
        respBody.append("<br>");
        respBody.append(req.getContextPath());
        respBody.append("<br>");
        respBody.append(req.getQueryString());
        respBody.append("<br>");
        respBody.append("<h3>headers:</h3>");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            respBody.append(headerName).append(" ");
            respBody.append(req.getHeader(headerName));
            respBody.append("<br>");
        }
        resp.getWriter().write(respBody.toString());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
//        Writer w = resp.getWriter();
//        w.write("你好");

//        ObjectMapper objectMapper = new ObjectMapper();
//        User user = objectMapper.readValue(req.getInputStream(), User.class);
//        System.out.println(user);
//        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write("id");
    }

    private static String readBody(HttpServletRequest req) throws IOException {
        int contentLength = req.getContentLength();
        byte[] buffer = new byte[contentLength];
        InputStream inputStream = req.getInputStream();
        inputStream.read(buffer);
        return new String(buffer, "utf-8");
    }
}
