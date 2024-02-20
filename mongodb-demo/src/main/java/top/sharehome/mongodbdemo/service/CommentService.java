package top.sharehome.mongodbdemo.service;

import org.springframework.data.domain.Page;
import top.sharehome.mongodbdemo.model.entity.Comment;

import java.util.List;

/**
 * 评论Service接口
 *
 * @author AntonyCheng
 */
public interface CommentService {

    /**
     * 保存一个评论
     */
    void saveComment(Comment comment);

    /**
     * 更新评论
     */
    void updateComment(Comment comment);

    /**
     * 根据id删除评论
     */
    void deleteCommentById(String id);

    /**
     * 查询所有评论
     */
    List<Comment> findCommentList();

    /**
     * 根据id查询评论
     */
    Comment findCommentById(String id);

    /**
     * 分页
     */
    Page<Comment> pageComment(int page,int size);

}
