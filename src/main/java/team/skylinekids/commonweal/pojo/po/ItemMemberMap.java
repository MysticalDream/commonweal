package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 项目成员映射表
 *
 * @author MysticalDream
 */
@TableName(value = "item_and_member_map")
public class ItemMemberMap {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "id", update = false, insert = false)
    private Integer id;
    /**
     * 项目id
     */
    private Integer item_id;
    /**
     * 目标id
     */
    private Integer target_id;
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
     * 成员类型 0---团队 1----个人
     */
    private Boolean type;
    /**
     * 审核状态
     */
    private Boolean status;

    public ItemMemberMap(Integer id, Integer item_id, Integer target_id, LocalDateTime gmtCreate, LocalDateTime gmtModified, Boolean available, Boolean type, Boolean status) {
        this.id = id;
        this.item_id = item_id;
        this.target_id = target_id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.available = available;
        this.type = type;
        this.status = status;
    }

    public ItemMemberMap() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
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

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
