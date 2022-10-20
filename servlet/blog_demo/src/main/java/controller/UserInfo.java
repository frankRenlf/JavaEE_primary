package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BlogDao;
import dao.UserDao;
import model.Blog;
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
 * @createTime : 2022/10/20 10:39
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
@WebServlet("/pages/userinfo")
public class UserInfo extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogId = req.getParameter("blogId");
        UserDao userDao = new UserDao();
        BlogDao blogDao = new BlogDao();
        if (blogId == null) {
            // 请求来自博客列表页, 直接返回登录的用户信息.
            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.setContentType("text/html;charset=utf8");
                resp.getWriter().write("当前未登录!");
                return;
            }
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                resp.setContentType("text/html;charset=utf8");
                resp.getWriter().write("当前未登录!");
                return;
            }
            resp.setContentType("application/json; charset=utf8");
            User user = userDao.selectById(userId);
            String jsonString = objectMapper.writeValueAsString(new User(userId, user.getUsername(), blogDao.getNums(userId).toString()));
            resp.getWriter().write(jsonString);
        } else {
            // 请求来自博客详情页, 返回文章作者信息.
            Blog blog = blogDao.getById(Integer.parseInt(blogId));
            if (blog == null) {
                resp.setContentType("text/html;charset=utf8");
                resp.getWriter().write("当前 blogId 有误!");
                return;
            }
            User user = userDao.selectById(blog.getUserId());
            if (user == null) {
                resp.setContentType("text/html;charset=utf8");
                resp.getWriter().write("当前博客对应的作者没有找到!");
                return;
            }
            resp.setContentType("application/json; charset=utf8");
            String jsonString = objectMapper.writeValueAsString(new User(user.getUserId(), user.getUsername(), blogDao.getNums(user.getUserId()).toString()));
            resp.getWriter().write(jsonString);
        }
    }
}
