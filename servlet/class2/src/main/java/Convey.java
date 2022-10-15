import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

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
class Couple {
    String s1;
    String s2;
}

@WebServlet("/msg")
public class Convey extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s1 = req.getParameter("s1");
        String s2 = req.getParameter("s2");
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(s1 + "say hello to" + s2);
    }


    private static String readBody(HttpServletRequest req) throws IOException {
        int contentLength = req.getContentLength();
        byte[] buffer = new byte[contentLength];
        InputStream inputStream = req.getInputStream();
        inputStream.read(buffer);
        return new String(buffer, "utf-8");
    }
}
