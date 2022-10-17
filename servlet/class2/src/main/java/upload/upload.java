package upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.text.html.HTML;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : servlet
 * @Package : upload
 * @createTime : 2022/10/17 19:01
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
@MultipartConfig
@WebServlet("/upload")
public class upload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file1");
        System.out.println(part.getSubmittedFileName());
        System.out.println(part.getSize());
        System.out.println(part.getContentType());
        part.write("C:\\Users\\11195\\IdeaProjects\\JavaEE_primary\\servlet\\class2\\src\\main\\webapp\\images\\res.jpg");
        resp.setContentType("text/html");
        resp.getWriter().write("success");
    }
}
