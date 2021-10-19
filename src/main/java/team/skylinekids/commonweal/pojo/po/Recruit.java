package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;

import java.time.LocalDateTime;

/**
 * 招募志愿者信息
 *
 * @author MysticalDream
 */
public class Recruit {
    /**
     * 招募志愿id
     */
    @TableId
    @TableField(value = "recruit_id",insert = false,update = false)
    private Integer recruitId;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 志愿开始时间
     */
    private LocalDateTime gmtStart;
    /**
     * 结束时间
     */
    private LocalDateTime gmtEnd;
    /**
     * 记录生成时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 记录修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 最大人数限制
     */
    private Integer maxMen;
    /**
     * 当前人数
     */
    private Integer nowMen;
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
    /**
     * 状态 0---待招募 1---招募中 2---已招募完成
     */
    private Integer status;


}
