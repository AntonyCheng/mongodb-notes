package top.sharehome.mongodbdemo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import top.sharehome.mongodbdemo.model.entity.Comment;
import top.sharehome.mongodbdemo.repository.CommentRepository;
import top.sharehome.mongodbdemo.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论Service接口实现类
 *
 * @author AntonyCheng
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentRepository commentRepository;

    /**
     * 保存评论
     */
    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 更新评论
     */
    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 根据Id删除评论
     */
    @Override
    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }

    /**
     * 查询所有评论
     */
    @Override
    public List<Comment> findCommentList() {
        return commentRepository.findAll();
    }

    /**
     * 根据Id查询评论
     */
    @Override
    public Comment findCommentById(String id) {
        return commentRepository.findById(id).get();
    }

    /**
     * 分页
     */
    @Override
    public Page<Comment> pageComment(int page, int size) {
        // PageRequest 的页码是从0开始的
        return commentRepository.findAllBy(PageRequest.of(page - 1, size));
    }

}
