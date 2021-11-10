package team.skylinekids.commonweal.utils;

import team.skylinekids.commonweal.enums.MyTimeUnit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public final String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    public final String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
    public final String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    public final String hour_24 = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    public final String hour_12 = String.valueOf(Calendar.getInstance().get(Calendar.HOUR));
    public final String minute = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
    public final String second = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
    public final String week_of_year = String.valueOf(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
    public final String week_of_month = String.valueOf(Calendar.getInstance().get(Calendar.WEEK_OF_MONTH));
    public final String day_of_year = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
    public final String day_of_month = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    public final String day_of_week = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));


    /**
     * 获取当前时间
     *
     * @return 当前时间, 24小时yyyy-MM-dd HH:mm:ss格式
     */
    public static String getTime24() {
        Date date = new Date();
        SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = timeStamp.format(date);
        return time;
    }

    /**
     * 获取当前时间的时间戳
     *
     * @return Unix时间戳
     */
    public Long getStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 将Unix时间戳转换为日期时间
     *
     * @param timeStamp Unix时间戳
     * @return 日期时间yyyy-MM-dd HH:mm:ss
     */
    public String stamp2Time(long timeStamp) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeStamp * 1000));
    }

    /**
     * 计算年龄
     *
     * @param year 出生年份
     * @return 年龄
     */
    public int getAge(int year) {
        return Integer.parseInt(this.year) - year;
    }

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

    /**
     * 获取时间戳
     *
     * @param amount
     * @param myTimeUnit
     * @return
     */
    public static long getDistanceTimeStamp(long amount, MyTimeUnit myTimeUnit) {
        switch (myTimeUnit) {
            case DAY:
                return amount * 86400000;
            case MONTH:
                return amount * 2592000000L;
            case YEAR:
                return amount * 31104000000L;
            case HOUR:
                return amount * 3600000;
            default:
                return -1;
        }
    }
}
