package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

import java.time.LocalDateTime;

/**
 * 项目
 *
 * @author MysticalDream
 */
public class Item {
    /**
     * 项目id
     */
    @TableId
    @TableField(value = "item_id", insert = false, update = false)
    private Integer itemId;
    /**
     * 项目标题
     */
    private String itemTitle;
    /**
     * 项目简介
     */
    private String itemIntroduction;
    /**
     * 项目创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 最后修改信息时间
     */
    private LocalDateTime gmtModified;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 项目是否可用
     */
    private Boolean isAvailable;
    /**
     * 项目封面地址
     */
    private String coverUrl;
    /**
     * 项目进行地址
     */
    private String location;

    /**
     * 项目预计持续时间最小单位是小时
     */
    private Long duration;
    /**
     * 项目分类
     */
    private Integer itemCategoryId;

    public Item(Integer itemId, String itemTitle, String itemIntroduction, LocalDateTime gmtCreate, LocalDateTime gmtModified, Integer userId, Boolean isAvailable, String coverUrl, String location, Long duration, Integer itemCategoryId) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemIntroduction = itemIntroduction;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.userId = userId;
        this.isAvailable = isAvailable;
        this.coverUrl = coverUrl;
        this.location = location;
        this.duration = duration;
        this.itemCategoryId = itemCategoryId;
    }

    public Item() {
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemIntroduction() {
        return itemIntroduction;
    }

    public void setItemIntroduction(String itemIntroduction) {
        this.itemIntroduction = itemIntroduction;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }
}
