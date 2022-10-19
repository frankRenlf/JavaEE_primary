package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BlogDao;
import model.Blog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : servlet
 * @Package : controller
 * @createTime : 2022/10/18 12:30
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
@WebServlet("/pages/blog")
public class BlogServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf8");
        String blogId = req.getParameter("blogId");
        BlogDao blogDao = new BlogDao();
        if (blogId == null) {
            List<Blog> blogList = blogDao.selectAll();
            String json = objectMapper.writeValueAsString(blogList);
            resp.getWriter().write(json);
        } else {
            Blog blog = blogDao.getById(Integer.parseInt(blogId));
            String json = objectMapper.writeValueAsString(blog);
            resp.getWriter().write(json);
        }

    }
}
