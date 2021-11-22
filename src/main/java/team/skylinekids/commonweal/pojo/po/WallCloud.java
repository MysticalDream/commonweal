package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 云墙
 *
 * @author MysticalDream
 */
@TableName("wall_cloud")
public class WallCloud {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "id", update = false, insert = false)
    private Integer id;
    /**
     * 内容
     */
    private String content;
    /**
     * 署名
     */
    private String signature;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 卡片id
     */
    private Integer cardId;
    /**
     * 标志 1-孩子们的愿望 0-寄语期望
     */
    private Boolean flag;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 是否可用
     */
    private Boolean isAvailable;

    public WallCloud(Integer id, String content, String signature, Integer userId, Integer cardId, Boolean flag, LocalDateTime gmtCreate, LocalDateTime gmtModified, Boolean isAvailable) {
        this.id = id;
        this.content = content;
        this.signature = signature;
        this.userId = userId;
        this.cardId = cardId;
        this.flag = flag;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.isAvailable = isAvailable;
    }

    public WallCloud() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
