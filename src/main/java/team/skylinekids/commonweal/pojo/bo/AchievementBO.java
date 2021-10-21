package team.skylinekids.commonweal.pojo.bo;

import team.skylinekids.commonweal.utils.convert.IgnoreConvert;

/**
 * 成就
 *
 * @author MysticalDream
 */
public class AchievementBO {
    /**
     * 成就介绍
     */
    private String introduction;
    /**
     * 对应项目或则成就的id
     */
    @IgnoreConvert
    private Integer typeId;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 对应成就类型代号 1--项目成就 2----团队成就
     */
    @IgnoreConvert
    private Integer type;
    /**
     * 成就标题
     */
    private String title;

    public AchievementBO(String introduction, Integer typeId, String coverUrl, Integer type, String title) {
        this.introduction = introduction;
        this.typeId = typeId;
        this.coverUrl = coverUrl;
        this.type = type;
        this.title = title;
    }

    public AchievementBO() {
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
