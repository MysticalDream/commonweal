package team.skylinekids.commonweal.enums;

/**
 * 时间单位
 *
 * @author MysticalDream
 */

public enum MyTimeUnit {
    /**
     * 年
     */
    YEAR("年"),
    /**
     * 月
     */
    MONTH("月"),
    /**
     * 天
     */
    DAY("天"),
    /**
     * 小时
     */
    HOUR("时");
    /**
     * 描述词
     */
    String description;

    MyTimeUnit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
