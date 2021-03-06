package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 团队成员映射表
 *
 * @author MysticalDream
 */
@TableName("team_and_user_map")
public class TeamMemberMap implements Serializable {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "id", update = false, insert = false)
    private Integer id;
    /**
     * 团队id
     */
    private Integer teamId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 加入时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 退出时间
     */
    private LocalDateTime gmtModified;
    /**
     * 该记录是否可用
     */
    @TableField("is_available")
    private Boolean available;
    /**
     * 审核状态 0-未审核 1-审核通过 2-审核未通过
     */
    private Integer status;
    /**
     * 读取状态
     */
    @TableField(value = "is_read")
    private Boolean readStatus;

    public TeamMemberMap(Integer id, Integer teamId, Integer userId, LocalDateTime gmtCreate, LocalDateTime gmtModified, Boolean available, Integer status, Boolean readStatus) {
        this.id = id;
        this.teamId = teamId;
        this.userId = userId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.available = available;
        this.status = status;
        this.readStatus = readStatus;
    }

    public TeamMemberMap() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Boolean readStatus) {
        this.readStatus = readStatus;
    }
}
