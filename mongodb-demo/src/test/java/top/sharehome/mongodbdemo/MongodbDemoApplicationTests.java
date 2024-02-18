package top.sharehome.mongodbdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import top.sharehome.mongodbdemo.model.entity.Comment;
import top.sharehome.mongodbdemo.service.CommentService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
class MongodbDemoApplicationTests {

    @Resource
    private CommentService commentService;

    @Test
    void save() {
        Comment comment = new Comment()
                .setArticleId("100000")
                .setContent("添加测试数据")
                .setCreateDateTime(LocalDateTime.now())
                .setUserId("1001")
                .setNickname("AntonyCheng")
                .setState("1")
                .setLikeNum(0)
                .setReplyNum(0);
        commentService.saveComment(comment);
    }

    @Test
    void list() {
        System.out.println(commentService.findCommentList());
    }

    @Test
    void get() {
        System.out.println(commentService.findCommentById("65d223992de08a2c5c0426df"));
    }

    @Test
    void update() {
        Comment comment = new Comment()
                .setId("65d223992de08a2c5c0426df")
                .setContent("修改测试数据")
                .setNickname("ANTONYCHENG");
        commentService.updateComment(comment);
        System.out.println(commentService.findCommentById("65d223992de08a2c5c0426df"));
    }

    @Test
    void delete() {
        commentService.deleteCommentById("65d223992de08a2c5c0426df");
        System.out.println(commentService.findCommentList());
    }

    @Test
    void page() {
        String parentId = "1";
        Comment comment1 = new Comment()
                .setArticleId("100000")
                .setContent("添加测试数据1")
                .setCreateDateTime(LocalDateTime.now())
                .setUserId("1001")
                .setNickname("AntonyCheng")
                .setState("1")
                .setLikeNum(0)
                .setReplyNum(0)
                .setParentId(parentId);
        Comment comment2 = new Comment()
                .setArticleId("100001")
                .setContent("添加测试数据2")
                .setCreateDateTime(LocalDateTime.now())
                .setUserId("1001")
                .setNickname("AntonyCheng")
                .setState("1")
                .setLikeNum(0)
                .setReplyNum(0)
                .setParentId(parentId);
        commentService.saveComment(comment1);
        commentService.saveComment(comment2);
        Page<Comment> comments = commentService.pageComment(parentId, 1, 5);
        System.out.println("总数：" + comments.getTotalElements());
        System.out.println("总页数：" + comments.getTotalPages());
        System.out.println("当前数据：" + comments.getContent());
    }

}