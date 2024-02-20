package top.sharehome.mongodbdemo.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import top.sharehome.mongodbdemo.model.entity.Comment;

/**
 * 评论Repository接口
 *
 * @author AntonyCheng
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    /**
     * 根据上级Id分页
     */
    Page<Comment> findByParentId(String parentId, Pageable pageable);

    Page<Comment> findAllBy(Pageable pageable);
}
