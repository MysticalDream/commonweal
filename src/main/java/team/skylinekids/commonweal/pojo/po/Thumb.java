package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

/**
 * 点赞表
 *
 * @author MysticalDream
 */
public class Thumb {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "id", insert = false, update = false)
    private Integer id;
    /**
     * 成就id
     */
    private Integer achievementId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 点赞状态 0 --取消,1--点赞
     */
    private Boolean status;

    public Thumb(Integer id, Integer achievementId, Integer userId, Boolean status) {
        this.id = id;
        this.achievementId = achievementId;
        this.userId = userId;
        this.status = status;
    }

    public Thumb() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Thumb{" +
                "id=" + id +
                ", achievementId=" + achievementId +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}
