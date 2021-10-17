package team.skylinekids.commonweal.pojo.dto;

import com.google.gson.annotations.SerializedName;
import team.skylinekids.commonweal.utils.convert.IgnoreConvert;

import java.time.LocalDateTime;

/**
 * 项目
 *
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
     * 项目创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 拥有者用户id 项目发起者
     */
    private Integer userId;
    /**
     * 项目封面地址
     */
    private String coverUrl;
    /**
     * 项目预计持续时间中文描述
     */
    private String duration;
    /**
     * 项目分类 描述
     */
    @IgnoreConvert
    private String itemCategory;
    /**
     * 最大人数限制
     */
    @SerializedName("maximumNumberLimit")
    private Integer maxMen;
    /**
     * 当前项目人数
     */
    private Integer nowMen;
    /**
     * 省份地区
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 县区
     */
    private String area;
    /**
     * 项目状态 1---进行中 2----结束
     */
    private Integer status;

    public ItemDTO(Integer itemId, String itemTitle, String itemIntroduction, LocalDateTime gmtCreate, Integer userId, String coverUrl, String duration, String itemCategory, Integer maxMen, Integer nowMen, String province, String city, String area, Integer status) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemIntroduction = itemIntroduction;
        this.gmtCreate = gmtCreate;
        this.userId = userId;
        this.coverUrl = coverUrl;
        this.duration = duration;
        this.itemCategory = itemCategory;
        this.maxMen = maxMen;
        this.nowMen = nowMen;
        this.province = province;
        this.city = city;
        this.area = area;
        this.status = status;
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

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemIntroduction='" + itemIntroduction + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", userId=" + userId +
                ", coverUrl='" + coverUrl + '\'' +
                ", duration='" + duration + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", maxMen=" + maxMen +
                ", nowMen=" + nowMen +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", status=" + status +
                '}';
    }
}
