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




// 用来处理登录请求
@WebServlet("/login2")
public class LoginServlet extends HttpServlet {
    // 由于请求的 form 中是一个 POST 请求, 因此这里就使用 doPost 方法来处理.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        // 读取请求中的参数, 判定当前用户的身份信息是否正确.
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || username.equals("") || password == null || password.equals("")) {
            // 返回一个提示.
            resp.getWriter().write("用户名或者密码不完整! 登录失败!");
            return;
        }
        // 验证用户名密码是否正确.
        // 一般来说用户名密码是存在于数据库中的.
        if (!username.equals("222") || !password.equals("222")) {
            // 登录失败!
            resp.getWriter().write("用户名或者密码错误! 登录失败!");
            return;
        }
        // 登录成功
        // 就需要创建一个 会话 (session), 把用户信息, 填写到 session 中!
        HttpSession session = req.getSession(true);
        session.setAttribute("username", "zhangsan");
        // 第二个参数得是 Object, 虽然写的是 0, 但是自动装箱成 Integer 了. 所以取的时候也是取出 Integer.
        Integer visitCount = (Integer) session.getAttribute("cnt");
        if (visitCount == null) {
            session.setAttribute("cnt", 0);
        } else {
            // 不做处理~~
        }
        // 让页面跳转到登录页面. 这里的重定向跳转, 是 GET 请求.
        resp.sendRedirect("index");
    }
}
