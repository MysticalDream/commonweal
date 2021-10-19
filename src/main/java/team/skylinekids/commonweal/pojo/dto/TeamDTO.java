package team.skylinekids.commonweal.pojo.dto;

import java.time.LocalDateTime;

/**
 * 队伍DTO
 *
 * @author MysticalDream
 */
public class TeamDTO {

    /**
     * 队伍id
     */
    private Integer teamId;
    /**
     * 队伍名称
     */
    private String teamName;
    /**
     * 队伍介绍
     */
    private String teamIntroduction;
    /**
     * 队伍注册时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 队长id
     */
    private Integer userId;
    /**
     * 队伍最大人数限制
     */
    private Integer maxMen;
    /**
     * 队伍当前人数
     */
    private Integer nowMen;
    /**
     * 队伍头像
     */
    private String teamAvatar;
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

    public TeamDTO(Integer teamId, String teamName, String teamIntroduction, LocalDateTime gmtCreate, Integer userId, Integer maxMen, Integer nowMen, String teamAvatar, String province, String city, String area) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamIntroduction = teamIntroduction;
        this.gmtCreate = gmtCreate;
        this.userId = userId;
        this.maxMen = maxMen;
        this.nowMen = nowMen;
        this.teamAvatar = teamAvatar;
        this.province = province;
        this.city = city;
        this.area = area;
    }

    public TeamDTO() {
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamIntroduction() {
        return teamIntroduction;
    }

    public void setTeamIntroduction(String teamIntroduction) {
        this.teamIntroduction = teamIntroduction;
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

    public String getTeamAvatar() {
        return teamAvatar;
    }

    public void setTeamAvatar(String teamAvatar) {
        this.teamAvatar = teamAvatar;
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
