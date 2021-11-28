package team.skylinekids.commonweal.enums;

/**
 * 等级码
 *
 * @author MysticalDream
 */

public enum LevelCode {
    /**
     * 普通等级 -1
     */
    COMMON_LEVEL(-1),
    /**
     * 普通登录等级 0
     */
    COMMON_LOGIN_LEVEL(0),
    /**
     * 普通管理员
     */
    COMMON_ADMIN_LEVEL(1),
    /**
     * 特别登录等级 10
     */
    SPECIAL_LOGIN_LEVEL(10);

    int level;

    LevelCode(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
