package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 用户额外信息
 *
 * @author MysticalDream
 */
@TableName("user_info")
public class UserInfo {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "user_info_id", insert = false, update = false)
    private Integer userInfoId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区域
     */
    private String area;
    /**
     * 乡镇
     */
    private String town;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 职业信息
     */
    private String profession;

    public UserInfo(Integer userInfoId, Integer userId, LocalDateTime gmtCreate, LocalDateTime gmtModified, String idNumber, String realName, String tel, String province, String city, String area, String town, String address, String profession) {
        this.userInfoId = userInfoId;
        this.userId = userId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.idNumber = idNumber;
        this.realName = realName;
        this.tel = tel;
        this.province = province;
        this.city = city;
        this.area = area;
        this.town = town;
        this.address = address;
        this.profession = profession;
    }

    public UserInfo() {

    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
