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
}
