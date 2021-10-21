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

}
