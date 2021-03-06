package team.skylinekids.commonweal.enums;

/**
 * 资源路径常量定义
 *
 * @author MysticalDream
 */
public interface ResourcePathConstant {

    /**
     * 磁盘图片基本路径
     */
    String DISK_IMAGE_BASE = "D:/commonweal/images/";
    /**
     * 用户头像头像磁盘绝对路径基
     */
    String DISK_AVATAR_BASE_URL = DISK_IMAGE_BASE + "avatars/user/";
    /**
     * 项目封面暂存
     */
    String DISK_ITEM_COVER_TEMP_BASE_URL = DISK_IMAGE_BASE + "temp/item/";
    /**
     * 项目封面
     */
    String DISK_ITEM_COVER_BASE_URL = DISK_IMAGE_BASE + "avatars/item/";
    /**
     * 队伍头像暂存
     */
    String DISK_TEAM_COVER_TEMP_BASE_URL = DISK_IMAGE_BASE + "temp/team/";
    /**
     * 队伍头像
     */
    String DISK_TEAM_COVER_BASEURL = DISK_IMAGE_BASE + "avatars/team/";
    /**
     * 招募志愿封面暂存
     */
    String DISK_RECRUIT_COVER_TEMP_BASE_URL = DISK_IMAGE_BASE + "temp/recruit/";
    /**
     * 招募志愿封面
     */
    String DISK_RECRUIT_COVER_BASE_URL = DISK_IMAGE_BASE + "recruit/";
    /**
     * 成就图片暂存
     */
    String DISK_ACHIEVEMENT_TEMP_IMG_BASE = DISK_IMAGE_BASE + "temp/achievements/";
    /**
     * 成就图片
     */
    String DISK_ACHIEVEMENT_IMG_BASE = DISK_IMAGE_BASE + "achievements/";
    /**
     * 助农暂存
     */
    String DISK_FARMER_TEMP_IMG_BASE = DISK_IMAGE_BASE + "temp/farmer/";
    /**
     * 助农
     */
    String DISK_FARMER_IMG_BASE = DISK_IMAGE_BASE + "farmer/";
    /**
     * 领养动物暂存
     */
    String DISK_ADOPT_TEMP_IMG_BASE = DISK_IMAGE_BASE + "temp/adopt/";
    /**
     * 领养动物
     */
    String DISk_ADOPT_IMG_BASE = DISK_IMAGE_BASE + "adopt/";
    /**
     * 直播暂存
     */
    String DISK_LIVE_TEMP_BASE = DISK_IMAGE_BASE + "temp/live/";
    /**
     * 直播
     */
    String DISK_LIVE_IMG_BASE = DISK_IMAGE_BASE + "live/";
    /**
     * 反馈暂存
     */
    String DISK_FEEDBACK_TEMP_BASE = DISK_IMAGE_BASE + "temp/feedback/";
    /**
     * 反馈
     */
    String DISK_FEEDBACK_IMG_BASE = DISK_IMAGE_BASE + "feedback/";

    //--------------------------------------------------------------------------
    /**
     * 虚拟路径映射
     */
    String ROOT = "/upload";
    /**
     * 图片路径
     */
    String VIRTUAL_IMAGE_BASE = ROOT + "/images/";

    /**
     * 头像客户端显示基路径
     */
    String VIRTUAL_USER_AVATAR_URL_BASE = VIRTUAL_IMAGE_BASE + "avatars/user/";
    /**
     * 成就图片暂存
     */
    String VIRTUAL_ACHIEVEMENT_TEMP_IMG_BASE = VIRTUAL_IMAGE_BASE + "temp/achievements/";
    /**
     * 成就图片路径
     */
    String VIRTUAL_ACHIEVEMENT_IMG_BASE = VIRTUAL_IMAGE_BASE + "achievements/";
    /**
     * 项目封面暂存
     */
    String VIRTUAL_ITEM_COVER_TEMP_BASE = VIRTUAL_IMAGE_BASE + "temp/item/";
    /**
     * 项目封面
     */
    String VIRTUAL_ITEM_COVER_BASE = VIRTUAL_IMAGE_BASE + "avatars/item/";
    /**
     * 队伍封面暂存
     */
    String VIRTUAL_TEAM_COVER_TEMP_BASE = VIRTUAL_IMAGE_BASE + "temp/team/";
    /**
     * 队伍封面
     */
    String VIRTUAL_TEAM_COVER_BASE = VIRTUAL_IMAGE_BASE + "avatars/team/";
    /**
     * 招募志愿暂存
     */
    String VIRTUAL_RECRUIT_COVER_TEMP_BASE = VIRTUAL_IMAGE_BASE + "temp/recruit/";
    /**
     * 招募志愿
     */
    String VIRTUAL_RECRUIT_COVER_BASE = VIRTUAL_IMAGE_BASE + "recruit/";
    /**
     * 助农暂存
     */
    String VIRTUAL_FARMER_COVER_TEMP_BASE = VIRTUAL_IMAGE_BASE + "temp/farmer/";
    /**
     * 助农信息
     */
    String VIRTUAL_FARMER_COVER_BASE = VIRTUAL_IMAGE_BASE + "farmer/";
    /**
     * 领养动物暂存
     */
    String VIRTUAL_ADOPT_COVER_TEMP_BASE = VIRTUAL_IMAGE_BASE + "temp/adopt/";
    /**
     * 领养动物
     */
    String VIRTUAL_ADOPT_COVER_BASE = VIRTUAL_IMAGE_BASE + "adopt/";
    /**
     * 直播暂存
     */
    String VIRTUAL_LIVE_COVER_TEMP_BASE = VIRTUAL_IMAGE_BASE + "temp/live/";
    /**
     * 直播
     */
    String VIRTUAL_LIVE_COVER_BASE = VIRTUAL_IMAGE_BASE + "live/";
    /**
     * 反馈暂存
     */
    String VIRTUAL_FEEDBACK_TEMP_BASE = VIRTUAL_IMAGE_BASE + "temp/feedback/";
    /**
     * 反馈
     */
    String VIRTUAL_FEEDBACK_BASE = VIRTUAL_IMAGE_BASE + "feedback/";

}
