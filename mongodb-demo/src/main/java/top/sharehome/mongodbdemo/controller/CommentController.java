package top.sharehome.mongodbdemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import top.sharehome.mongodbdemo.model.entity.Comment;
import top.sharehome.mongodbdemo.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论Controller类
 *
 * @author AntonyCheng
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 保存评论
     */
    @PostMapping("/save")
    public String save(@RequestBody Comment comment) {
        commentService.saveComment(comment);
        return "保存成功";
    }

    /**
     * 更新评论
     */
    @PutMapping("/update")
    public String update(@RequestBody Comment comment) {
        commentService.updateComment(comment);
        return "更新成功";
    }

    /**
     * 根据Id删除评论
     */
    @DeleteMapping("/delete")
    public String delete(String id) {
        commentService.deleteCommentById(id);
        return "删除成功";
    }

    /**
     * 查询所有评论
     */
    @GetMapping("/list")
    public List<Comment> list() {
        return commentService.findCommentList();
    }

    /**
     * 根据Id查询评论
     */
    @GetMapping("/get")
    public Comment get(String id) {
        return commentService.findCommentById(id);
    }

    /**
     * 根据上级Id分页
     */
    @GetMapping("/page")
    public Page<Comment> page(String parentId, int page, int size) {
        return commentService.pageComment(parentId, page, size);
    }

}
