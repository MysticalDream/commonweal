package team.skylinekids.commonweal.pojo.dto;


import java.time.LocalDateTime;

/**
 * 反馈评论传输
 *
 * @author MysticalDream
 */
public class FeedbackCommentDTO {
    /**
     * 自增id
     */
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
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 是否是领养者
     */
    private Boolean isAdopter;

    public FeedbackCommentDTO(Integer id, Integer userId, Integer feedbackId, String content, Integer praiseNum, LocalDateTime gmtCreate, Boolean isAdopter) {
        this.id = id;
        this.userId = userId;
        this.feedbackId = feedbackId;
        this.content = content;
        this.praiseNum = praiseNum;
        this.gmtCreate = gmtCreate;
        this.isAdopter = isAdopter;
    }

    public FeedbackCommentDTO() {
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

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getAdopter() {
        return isAdopter;
    }

    public void setAdopter(Boolean adopter) {
        isAdopter = adopter;
    }
}
