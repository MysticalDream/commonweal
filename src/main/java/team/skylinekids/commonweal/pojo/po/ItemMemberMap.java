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
    private Integer itemId;
    /**
     * 目标id
     */
    private Integer targetId;
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
     * 审核状态  0-未审核 1-审核通过 2-审核未通过
     */
    private Integer status;
    /**
     * 读取状态
     */
    @TableField(value = "is_read")
    private Boolean readStatus;

    public ItemMemberMap(Integer id, Integer itemId, Integer targetId, LocalDateTime gmtCreate, LocalDateTime gmtModified, Boolean available, Boolean type, Integer status, Boolean readStatus) {
        this.id = id;
        this.itemId = itemId;
        this.targetId = targetId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.available = available;
        this.type = type;
        this.status = status;
        this.readStatus = readStatus;
    }

    public ItemMemberMap() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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

    @Override
    public String toString() {
        return "ItemMemberMap{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", targetId=" + targetId +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", available=" + available +
                ", type=" + type +
                ", status=" + status +
                ", readStatus=" + readStatus +
                '}';
    }
}
