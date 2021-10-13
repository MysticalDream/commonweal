package team.skylinekids.commonweal.utils;

import team.skylinekids.commonweal.enums.MyTimeUnit;

/**
 * 时间工具
 *
 * @author MysticalDream
 */
public final class TimeUtils {
    /**
     * 一个月30天
     */
    private static final long MONTH_OF_DAY = 30L;
    /**
     * 一年有12个月
     */
    private static final long YEAR_OF_DAY = MONTH_OF_DAY * 12;
    /**
     * 一天有24小时
     */
    private static final long DAY_OF_HOUR = 24;

    private TimeUtils() {

    }

    /**
     * 将时间转换成小时
     *
     * @param amount
     * @param timeUnit
     * @return
     */
    public static Long countHours(long amount, MyTimeUnit timeUnit) {

        switch (timeUnit) {
            //天
            case DAY:
                return amount * DAY_OF_HOUR;
            //年
            case YEAR:
                return YEAR_OF_DAY * amount * DAY_OF_HOUR;
            //月
            case MONTH:
                return MONTH_OF_DAY * amount * DAY_OF_HOUR;
            //时
            default:
                return amount;
        }

    }

    /**
     * 根据小时转化为最小可用单位
     *
     * @param amount 小时
     * @return
     */
    public static String getDescriptiveByAmount(long amount) {
        if (amount < DAY_OF_HOUR) {
            //时
            return amount + " " + MyTimeUnit.HOUR.getDescription();
        } else if (amount < (MONTH_OF_DAY * DAY_OF_HOUR)) {
            //天
            return (amount / DAY_OF_HOUR) + " " + MyTimeUnit.DAY.getDescription();
        } else if (amount < (YEAR_OF_DAY * DAY_OF_HOUR)) {
            //月
            return amount / DAY_OF_HOUR / MONTH_OF_DAY + " " + MyTimeUnit.MONTH.getDescription();
        } else {
            //年
            return amount / YEAR_OF_DAY / DAY_OF_HOUR + " " + MyTimeUnit.YEAR.getDescription();
        }
    }
}
