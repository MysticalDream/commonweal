package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 助农信息
 *
 * @author MysticalDream
 */
@TableName("t_help_farmer_info")
public class FarmerInfo implements Serializable {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "id", insert = false, update = false)
    private Integer id;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 发布时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 最后修改信息时间
     */
    private LocalDateTime gmtModified;
    /**
     * 位置信息
     */
    private String location;
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

    public FarmerInfo(Integer id, String coverUrl, String introduction, String tel, String realName, LocalDateTime gmtCreate, LocalDateTime gmtModified, String location, String province, String city, String area) {
        this.id = id;
        this.coverUrl = coverUrl;
        this.introduction = introduction;
        this.tel = tel;
        this.realName = realName;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.location = location;
        this.province = province;
        this.city = city;
        this.area = area;
    }

    public FarmerInfo() {
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

}
