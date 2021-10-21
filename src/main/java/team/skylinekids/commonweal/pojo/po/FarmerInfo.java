package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 助农信息
 *
 * @author MysticalDream
 */
@TableName("t_help_farmer_info")
public class FarmerInfo {
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
}
