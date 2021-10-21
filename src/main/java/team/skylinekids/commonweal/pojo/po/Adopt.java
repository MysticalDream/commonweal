package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

/**
 * 动物领养信息
 *
 * @author MysticalDream
 */
public class Adopt {
    /**
     * 领养信息id
     */
    @TableId
    @TableField(value = "adopt_id", insert = false, update = false)
    private Integer adoptId;
    /**
     * 品种
     */
    private String variety;
    /**
     * 外貌
     */
    private String appearance;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性格
     */
    private String character;
    /**
     * 习性
     */
    private String habit;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 领养的用户id
     */
    private Integer userId;
    /**
     * 封面
     */
    private String coverUrl;
}
