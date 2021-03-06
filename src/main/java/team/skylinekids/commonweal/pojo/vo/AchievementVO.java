package team.skylinekids.commonweal.pojo.vo;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 成就展示
 *
 * @author MysticalDream
 */
@TableName("achievement")
public class AchievementVO {
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
     * 1--项目成就 2--团队成就
     */
    private Integer type;
    /**
     * 对应项目或则成就id
     */
    private Integer typeId;
    /**
     * 成就创建时间
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
    /**
     * 用户是否点赞 默认没有点赞
     */
    private Boolean loved = false;
    /**
     * 项目名称或队伍名称
     */
    private String name;

    public AchievementVO(Integer id, String coverUrl, String introduction, Integer type, Integer typeId, LocalDateTime gmtCreate, LocalDateTime gmtModified, Long loveNumber, String title, Boolean loved, String name) {
        this.id = id;
        this.coverUrl = coverUrl;
        this.introduction = introduction;
        this.type = type;
        this.typeId = typeId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.loveNumber = loveNumber;
        this.title = title;
        this.loved = loved;
        this.name = name;
    }

    public AchievementVO() {

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Boolean getLoved() {
        return loved;
    }

    public void setLoved(Boolean loved) {
        this.loved = loved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
