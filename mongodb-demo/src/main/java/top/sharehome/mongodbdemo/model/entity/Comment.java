package top.sharehome.mongodbdemo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 文章评论实体类
 * 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。
 * 使用@Document(collection="mongodb 对应 collection 名")
 * 若未加 @Document ，该 bean save 到 mongo 的 comment collection
 * 若添加 @Document ，则 save 到 comment collection
 * 复合索引使用@CompoundIndex( def = "{'userid': 1, 'nickname': -1}")
 *
 * @author AntonyCheng
 */
@Document(collection = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Comment implements Serializable {

    /**
     * 主键标识，该属性的值会自动对应mongodb的主键字段"_id"，如果该属性名就叫“id”,则该注解可以省略，否则必须写
     */
    @Id
    private String id;

    /**
     * 使用@Field注解使属性对应mongodb的字段的名字，如果一致，则无需该注解
     * 吐槽内容
     */
    @Field("content")
    private String content;

    /**
     * 发布日期
     */
    @Field("publishTime")
    private Date publishTime;

    /**
     * 使用@Indexed添加了一个单字段的索引
     * 发布人ID
     */
    @Indexed
    @Field("userId")
    private String userId;

    /**
     * 昵称
     */
    @Field("nickname")
    private String nickname;

    /**
     * 评论的日期时间
     */
    @Field("createDateTime")
    private LocalDateTime createDateTime;

    /**
     * 点赞数
     */
    @Field("likeNum")
    private Integer likeNum;

    /**
     * 回复数
     */
    @Field("replyNum")
    private Integer replyNum;

    /**
     * 状态
     */
    @Field("state")

    private String state;
    /**
     * 上级ID
     */
    @Field("parentId")
    private String parentId;

    /**
     * 文章ID
     */
    @Field("articleId")
    private String articleId;

    private static final long serialVersionUID = 7464206909786546731L;

}