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
     * 对应点赞的表的主键id
     */
    private Integer typeId;
    /**
     * 点赞的类型 1--项目成就 2--团队成就
     */
    private Integer type;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 点赞状态 0 --取消,1--点赞
     */
    private Boolean status;

    public Thumb(Integer id, Integer typeId, Integer type, Integer userId, Boolean status) {
        this.id = id;
        this.typeId = typeId;
        this.type = type;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
}
