package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    User userBase;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("text/html; charset=utf8");
        User user = objectMapper.readValue(req.getInputStream(), User.class);
        System.out.println(user);
        UserDao userDao = new UserDao();
        userBase = userDao.selectByName(user.getUsername());
        if (userBase == null) {
            resp.getWriter().write(objectMapper.writeValueAsString(new User(0, "0", "0")));
        } else {
            if (userBase.getPassword().equals(user.getPassword())) {
                resp.getWriter().write(objectMapper.writeValueAsString(new User(userBase.getUserId(), "0", "0")));
                HttpSession session = req.getSession(true);
                session.setAttribute("userId", userBase.getUserId());
            } else {
                resp.getWriter().write(objectMapper.writeValueAsString(new User(0, "0", "0")));
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session==null){
            resp.setStatus(403);
            return;
        }
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId==null){
            resp.setStatus(403);
        }else if (userId.equals(userBase.getUserId())){
            resp.setStatus(200);
        }else {
            resp.setStatus(403);
        }
    }
}
