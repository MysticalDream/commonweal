package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

import java.io.File;
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
     * 默认创建一个空的用户对象
     */
    public User() {
        this.available = true;
    }

    public User(int userId, String username, String password, String tel, LocalDateTime gmtCreate, LocalDateTime gmtModified, String avatarUrl, Boolean isAvailable) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.avatarUrl = avatarUrl;
        this.available = isAvailable;
    }

    public int getUserId() {
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
        this.avatarUrl = new File(avatarUrl).getName();
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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
                ", isAvailable=" + available +
                '}';
    }
}
