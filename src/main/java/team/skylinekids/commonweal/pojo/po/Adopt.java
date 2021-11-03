package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 动物领养信息
 *
 * @author MysticalDream
 */
public class Adopt implements Serializable {
    /**
     * 领养信息id
     */
    @TableId
    @TableField(value = "adopt_id", insert = false, update = false)
    private Integer adoptId;
    /**
     * 品种
     */
    private String variety;
    /**
     * 外貌
     */
    private String appearance;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性格
     */
    private String characters;
    /**
     * 习性
     */
    private String habit;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 领养的用户id
     */
    private Integer userId;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 生成时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 最后修改信息时间
     */
    private LocalDateTime gmtModified;

    public Adopt(Integer adoptId, String variety, String appearance, Integer age, String character, String habit, String introduction, Integer userId, String coverUrl, LocalDateTime gmtCreate, LocalDateTime gmtModified) {
        this.adoptId = adoptId;
        this.variety = variety;
        this.appearance = appearance;
        this.age = age;
        this.characters = character;
        this.habit = habit;
        this.introduction = introduction;
        this.userId = userId;
        this.coverUrl = coverUrl;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Adopt() {
    }

    public Integer getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(Integer adoptId) {
        this.adoptId = adoptId;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCharacter() {
        return characters;
    }

    public void setCharacter(String character) {
        this.characters = character;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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
}
