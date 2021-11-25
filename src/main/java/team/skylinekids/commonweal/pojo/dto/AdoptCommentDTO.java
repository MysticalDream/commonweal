package team.skylinekids.commonweal.pojo.dto;

import java.time.LocalDateTime;

/**
 * 领养评论传输
 *
 * @author MysticalDream
 */
public class AdoptCommentDTO {
    /**
     * 自增id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 领养动物id
     */
    private Integer adoptId;
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
     * 是不是领养者
     */
    private Boolean isAdopter;

    public AdoptCommentDTO(Integer id, Integer userId, Integer adoptId, String content, Integer praiseNum, LocalDateTime gmtCreate, Boolean isAdopter) {
        this.id = id;
        this.userId = userId;
        this.adoptId = adoptId;
        this.content = content;
        this.praiseNum = praiseNum;
        this.gmtCreate = gmtCreate;
        this.isAdopter = isAdopter;
    }

    public AdoptCommentDTO() {
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

    public Integer getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(Integer adoptId) {
        this.adoptId = adoptId;
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
