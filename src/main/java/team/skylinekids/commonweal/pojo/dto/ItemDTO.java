package team.skylinekids.commonweal.pojo.dto;

/**
 * @author MysticalDream
 */
public class ItemDTO {
    /**
     * 项目id
     */
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
     * 拥有者用户id 项目发起者
     */
    private Integer userId;
    /**
     * 项目封面地址
     */
    private String coverUrl;
    /**
     * 项目进行地址
     */
    private String location;

    /**
     * 项目预计持续时间中文描述
     */
    private String duration;
    /**
     * 项目分类 描述
     */
    private String itemCategory;
    /**
     * 最大人数限制
     */
    private Integer maxMen;
    /**
     * 当前项目人数
     */
    private Integer nowMen;

    public ItemDTO(Integer itemId, String itemTitle, String itemIntroduction, Integer userId, String coverUrl, String location, String duration, String itemCategory, Integer maxMen, Integer nowMen) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemIntroduction = itemIntroduction;
        this.userId = userId;
        this.coverUrl = coverUrl;
        this.location = location;
        this.duration = duration;
        this.itemCategory = itemCategory;
        this.maxMen = maxMen;
        this.nowMen = nowMen;
    }

    public ItemDTO() {
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

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
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
}
