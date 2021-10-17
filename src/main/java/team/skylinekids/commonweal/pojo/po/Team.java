package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;

import java.time.LocalDateTime;

/**
 * 队伍
 *
 * @author MysticalDream
 */
public class Team {
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
     * 队伍信息修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 队长id
     */
    private Integer userId;
    /**
     * 队伍是否可用
     */
    @TableField("is_available")
    private Boolean available;
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

    public Team(Integer teamId, String teamName, String teamIntroduction, LocalDateTime gmtCreate, LocalDateTime gmtModified, Integer userId, Boolean available, Integer maxMen, Integer nowMen, String teamAvatar) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamIntroduction = teamIntroduction;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.userId = userId;
        this.available = available;
        this.maxMen = maxMen;
        this.nowMen = nowMen;
        this.teamAvatar = teamAvatar;
    }

    public Team() {
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

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamIntroduction='" + teamIntroduction + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", userId=" + userId +
                ", available=" + available +
                ", maxMen=" + maxMen +
                ", nowMen=" + nowMen +
                ", teamAvatar='" + teamAvatar + '\'' +
                '}';
    }
}
