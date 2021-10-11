package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author MysticalDream
 */
public class User {
    /**
     * 用户id
     */
    @TableId
    @TableField(value = "user_id", insert = false, update = false)
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String tel;
    /**
     * 注册时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 最后修改信息时间
     */
    private LocalDateTime gmtModified;
    /**
     * 用户头像url
     */
    private String avatarUrl;
    /**
     * 用户是否可用
     */
    @TableField("is_available")
    private Boolean available;
    /**
     * 用户地区地址
     */
    private String location;

    /**
     * 默认创建一个空的用户对象
     */
    public User() {
        //默认可用
        this.available = true;
    }

    public User(Integer userId, String username, String password, String tel, LocalDateTime gmtCreate, LocalDateTime gmtModified, String avatarUrl, Boolean available, String location) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.avatarUrl = avatarUrl;
        this.available = available;
        this.location = location;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", available=" + available +
                ", location='" + location + '\'' +
                '}';
    }
}
