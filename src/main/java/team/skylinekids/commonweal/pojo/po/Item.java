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
     * 拥有者用户id
     */
    private Integer userId;
    /**
     * 项目是否可用
     */
    @TableField(value = "is_available")
    private Boolean available;
    /**
     * 项目封面地址
     */
    private String coverUrl;
    /**
     * 项目进行地址
     */
    private String location;
    /**
     * 项目预计持续时间
     */
    private String duration;
    /**
     * 项目分类
     */
    private Integer itemCategoryId;
    /**
     * 最大人数限制
     */
    private Integer maxMen;
    /**
     * 当前项目人数
     */
    private Integer nowMen;

    public Item() {
        this.available = true;
    }

    public Item(Integer itemId, String itemTitle, String itemIntroduction, LocalDateTime gmtCreate, LocalDateTime gmtModified, Integer userId, Boolean available, String coverUrl, String location, String duration, Integer itemCategoryId, Integer maxMen, Integer nowMen) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemIntroduction = itemIntroduction;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.userId = userId;
        this.available = available;
        this.coverUrl = coverUrl;
        this.location = location;
        this.duration = duration;
        this.itemCategoryId = itemCategoryId;
        this.maxMen = maxMen;
        this.nowMen = nowMen;
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
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public Integer getMaxMen() {
        return maxMen;
    }

    public void setMaxMen(Integer maxMen) {
        this.maxMen = maxMen;
    }

    public Integer getNowMen() {
        return nowMen;
    }

    public void setNowMen(Integer nowMen) {
        this.nowMen = nowMen;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemIntroduction='" + itemIntroduction + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", userId=" + userId +
                ", available=" + available +
                ", coverUrl='" + coverUrl + '\'' +
                ", location='" + location + '\'' +
                ", duration='" + duration + '\'' +
                ", itemCategoryId=" + itemCategoryId +
                ", maxMen=" + maxMen +
                ", nowMen=" + nowMen +
                '}';
    }
}
