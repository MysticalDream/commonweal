package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 项目成就
 *
 * @author MysticalDream
 */
@TableName("item_achievement")
public class ItemAchievement {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "id", update = false, insert = false)
    private Integer id;
    /**
     * 成就配图
     */
    private String coverUrl;
    /**
     * 成就文字说明
     */
    private String introduction;
    /**
     * 对应项目id
     */
    private Integer itemId;
    /**
     * 项目成就创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 最后修改信息时间
     */
    private LocalDateTime gmtModified;
    /**
     * 点赞数
     */
    private Long loveNumber;
    /**
     * 成就标题
     */
    private String title;

    public ItemAchievement(Integer id, String coverUrl, String introduction, Integer itemId, LocalDateTime gmtCreate, LocalDateTime gmtModified, Long loveNumber, String title) {
        this.id = id;
        this.coverUrl = coverUrl;
        this.introduction = introduction;
        this.itemId = itemId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.loveNumber = loveNumber;
        this.title = title;
    }

    public ItemAchievement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public Long getLoveNumber() {
        return loveNumber;
    }

    public void setLoveNumber(Long loveNumber) {
        this.loveNumber = loveNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
