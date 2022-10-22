package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : servlet
 * @Package : model
 * @createTime : 2022/10/17 20:30
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Blog {
    private Integer blogId;
    private String title;
    private String content;
    private Integer userId;
    private String postTime;

    public Blog() {
    }

    public Blog(Integer blogId, String title, String content, Integer userId, Timestamp postTime) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        if (postTime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.postTime = simpleDateFormat.format(postTime);
        }

    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.postTime = simpleDateFormat.format(postTime);
    }
}
