package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 领养反馈
 *
 * @author MysticalDream
 */
@TableName("adopt_feedback")
public class AdoptFeedback {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "feedback_id", insert = false, update = false)
    private Integer feedbackId;
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 反馈文本
     */
    private String content;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    public AdoptFeedback(Integer feedbackId, Integer userId, String content, LocalDateTime gmtCreate, LocalDateTime gmtModified) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.content = content;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public AdoptFeedback() {
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
