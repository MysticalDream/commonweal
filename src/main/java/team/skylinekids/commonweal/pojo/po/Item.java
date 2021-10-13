package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

import java.time.LocalDateTime;

/**
 * 项目
 *
 * @author MysticalDream
 */
public class Item {
    /**
     * 项目id
     */
    @TableId
    @TableField(value = "item_id", insert = false, update = false)
    private Integer itemId;
    /**
     * 项目标题
     */
    private String itemTitle;
    /**
     * 项目简介
     */
    private String itemIntroduction;
    /**
     * 项目创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 最后修改信息时间
     */
    private LocalDateTime gmtModified;
    /**
     * 拥有者用户id
     */
    private Integer userId;
    /**
     * 项目是否可用
     */
    @TableField(value = "is_available")
    private Boolean available;
    /**
     * 项目封面地址
     */
    private String coverUrl;
    /**
     * 项目进行地址
     */
    private String location;
    /**
     * 项目预计持续时间
     */
    private String duration;
    /**
     * 项目分类
     */
    private Integer itemCategoryId;

}
