package team.skylinekids.commonweal.pojo.dto;


import team.skylinekids.commonweal.enums.Constant;

/**
 * 用户数据传输对象
 *
 * @author MysticalDream
 */
public class UserDTO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String tel;

    /**
     * 用户头像url
     */
    private String avatarUrl;
    /**
     * 用户地区地址
     */
    private String location;

    public UserDTO() {

    }

    public UserDTO(Integer userId, String username, String tel, String avatarUrl, String location) {
        this.userId = userId;
        this.username = username;
        this.tel = tel;
        this.avatarUrl = avatarUrl;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 将用户头像名和头像路劲结合
     *
     * @return
     */
    public String getAvatarUrl() {
        return Constant.USER_AVATAR_URL_BASE + avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", tel='" + tel + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
