package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 反馈评论
 *
 * @author MysticalDream
 */
@TableName("feedback_comment")
public class FeedbackComment {

    /**
     * 自增id
     */
    @TableId
    @TableField(value = "id", update = false, insert = false)
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 反馈id
     */
    private Integer feedbackId;
    /**
     * 评论的内容
     */
    private String content;
    /**
     * 点赞数
     */
    private Integer praiseNum;
    /**
     * 是否置顶
     */
    @TableField(value = "is_top")
    private Integer top;
    /**
     * 是否可用
     */
    @TableField(value = "is_available")
    private Boolean available;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    public FeedbackComment(Integer id, Integer userId, Integer feedbackId, String content, Integer praiseNum, Integer top, Boolean available, LocalDateTime gmtCreate, LocalDateTime gmtModified) {
        this.id = id;
        this.userId = userId;
        this.feedbackId = feedbackId;
        this.content = content;
        this.praiseNum = praiseNum;
        this.top = top;
        this.available = available;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public FeedbackComment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }
}
