package login;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.DataInput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : servlet
 * @Package : login
 * @createTime : 2022/10/15 19:07
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */

class User {
    public String username;
    public String password;
    public Boolean status;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

class Result {
    String status;
    Object data;
}

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        String body = readBody(req);
        User user = objectMapper.readValue(req.getInputStream(), User.class);
        System.out.println(user);
        resp.setContentType("text/html; charset=utf-8");
        if (user.username == null || user.password == null) {
            user.status = false;
        } else user.status = user.username.equals("frank") && user.password.equals("123");
        System.out.println(user.status);
        HttpSession session = req.getSession(true);
        session.setAttribute("username", "frank");
        resp.sendRedirect("index");
//        objectMapper.writeValue(resp.getWriter(),user);
        //


    }

}
