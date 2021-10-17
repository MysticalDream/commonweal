package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

import java.time.LocalDateTime;

/**
 * 团队成就表
 *
 * @author MysticalDream
 */
public class TeamAchievement {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "id", update = false, insert = false)
    private Integer id;
    /**
     * 成就配图,保存多张图片分割符为",",比如"1.png,2.png,3.png"
     */
    private String coverUrl;
    /**
     * 成就文字说明
     */
    private String introduction;
    /**
     * 对应项目id
     */
    private Integer teamId;
    /**
     * 项目成就创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改信息时间
     */
    private LocalDateTime gmtModified;

}
