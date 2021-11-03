package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 招募志愿者信息
 *
 * @author MysticalDream
 */
public class Recruit implements Serializable {
    /**
     * 招募志愿id
     */
    @TableId
    @TableField(value = "recruit_id", insert = false, update = false)
    private Integer recruitId;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 志愿开始时间
     */
    private LocalDateTime gmtStart;
    /**
     * 结束时间
     */
    private LocalDateTime gmtEnd;
    /**
     * 记录生成时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 记录修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 举行地址
     */
    private String location;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 最大人数限制
     */
    private Integer maxMen;
    /**
     * 当前人数
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
     * 状态 0---待招募 1---招募中 2---已招募完成
     */
    private Integer status;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 标题
     */
    private String title;

    public Recruit(Integer recruitId, String introduction, LocalDateTime gmtStart, LocalDateTime gmtEnd, LocalDateTime gmtCreate, LocalDateTime gmtModified, String location, String coverUrl, Integer maxMen, Integer nowMen, String province, String city, String area, Integer status, Integer categoryId, String title) {
        this.recruitId = recruitId;
        this.introduction = introduction;
        this.gmtStart = gmtStart;
        this.gmtEnd = gmtEnd;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.location = location;
        this.coverUrl = coverUrl;
        this.maxMen = maxMen;
        this.nowMen = nowMen;
        this.province = province;
        this.city = city;
        this.area = area;
        this.status = status;
        this.categoryId = categoryId;
        this.title = title;
    }

    public Recruit() {
    }

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public LocalDateTime getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(LocalDateTime gmtStart) {
        this.gmtStart = gmtStart;
    }

    public LocalDateTime getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(LocalDateTime gmtEnd) {
        this.gmtEnd = gmtEnd;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
