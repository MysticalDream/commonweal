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

}
