package dao;

import model.Blog;
import model.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : servlet
 * @Package : dao
 * @createTime : 2022/10/17 20:37
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class BlogDao {


    public void insert(Blog blog) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into blog values(null, ?, ?, ?, now())";
            statement = connection.prepareStatement(sql);
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getContent());
            statement.setString(3, String.valueOf(blog.getUserId()));

            int ret = statement.executeUpdate();
            if (ret == 1) {
                System.out.println("insert success");
            } else {
                System.out.println("insert failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    public List<Blog> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Blog> blogList = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog(resultSet.getInt("blogId"),
                        resultSet.getString("title"),
                        resultSet.getString("content").length() > 100 ?
                                resultSet.getString("content").substring(0, 100) :
                                resultSet.getString("content"),
                        resultSet.getInt("userId"),
                        resultSet.getTimestamp("postTime"));
                blogList.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return blogList;
    }

    public Blog getById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Blog blog = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                blog = new Blog(resultSet.getInt("blogId"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getInt("userId"),
                        resultSet.getTimestamp("postTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return blog;
    }

    public void deleteById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            int ret = statement.executeUpdate();
            if (ret == 1) {
                System.out.println("delete success");
            } else {
                System.out.println("delete failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }


}
