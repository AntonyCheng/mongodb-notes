package top.sharehome.mongodbdemo.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import top.sharehome.mongodbdemo.model.entity.Comment;
import top.sharehome.mongodbdemo.repository.CommentRepository;
import top.sharehome.mongodbdemo.service.CommentUpgradeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论升级版Service接口实现类
 * 使用Spring Data提供的MongoTemplate进行操作
 *
 * @author AntonyCheng
 */
@Service
public class CommentUpgradeServiceImpl implements CommentUpgradeService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private CommentRepository commentRepository;

    /**
     * 保存评论
     */
    @Override
    public void saveComment(Comment comment) {
        Comment save = mongoTemplate.save(comment);
        System.out.println("被保存的评论：" + save);
    }

    /**
     * 点赞数+1
     */
    @Override
    public void updateComment(Comment comment) {
        // 构建更新对象
        Update update = new Update();
        update.inc("likeNum");
        // 构建查询对象
        Query query = Query.query(Criteria.where("_id").is(comment.getId()));
        UpdateResult updateResult = mongoTemplate.updateFirst(
                query,
                update,
                Comment.class
        );
        System.out.println("更新数量为：" + updateResult.getModifiedCount());
    }

    /**
     * 根据Id删除评论
     */
    @Override
    public void deleteCommentById(String id) {
        DeleteResult deleteResult = mongoTemplate.remove(
                Query.query(Criteria.where("_id").is(id)),
                Comment.class
        );
        System.out.println("被删除的条数：" + deleteResult.getDeletedCount());
    }

    /**
     * 查询所有评论
     */
    @Override
    public List<Comment> findCommentList() {
        return mongoTemplate.findAll(Comment.class);
    }

    /**
     * 根据Id查询评论
     */
    @Override
    public Comment findCommentById(String id) {
        return mongoTemplate.findById(id, Comment.class);
    }

    /**
     * MongoTemplate中没有分页方法，需要自己构造
     */
    @Override
    public Page<Comment> pageComment(String parentId, int page, int size) {
        return commentRepository.findByParentId(parentId, PageRequest.of(page, size));
    }

}
