package team.skylinekids.commonweal.pojo.dto;


import team.skylinekids.commonweal.enums.Constant;
import team.skylinekids.commonweal.pojo.po.User;

/**
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

    public UserDTO() {

    }

    public UserDTO(Integer userId, String username, String tel, String avatarUrl) {
        this.userId = userId;
        this.username = username;
        this.tel = tel;
        this.avatarUrl = Constant.IMG_URL_BASE + avatarUrl;
    }

    public UserDTO(User user) {
        if (user != null) {
            this.userId = user.getUserId();
            this.username = user.getUsername();
            this.tel = user.getTel();
            this.avatarUrl = Constant.IMG_URL_BASE + user.getAvatarUrl();
        }

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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = Constant.IMG_URL_BASE + avatarUrl;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", tel='" + tel + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
