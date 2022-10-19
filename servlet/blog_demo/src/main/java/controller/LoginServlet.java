package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : servlet
 * @Package : controller
 * @createTime : 2022/10/19 20:37
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
@WebServlet("/pages/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("text/html; charset=utf8");
        User user = objectMapper.readValue(req.getInputStream(), User.class);
        System.out.println(user);
        UserDao userDao = new UserDao();
        User userBase = userDao.selectByName(user.getUsername());
        if (userBase == null) {
            resp.getWriter().write(objectMapper.writeValueAsString(new User(0, "0", "0")));
        } else {
            if (userBase.getPassword().equals(user.getPassword())) {
                resp.getWriter().write(objectMapper.writeValueAsString(new User(userBase.getUserId(), "0", "0")));
            } else {
                resp.getWriter().write(objectMapper.writeValueAsString(new User(0, "0", "0")));
            }
        }

    }
}
