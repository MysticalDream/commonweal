package team.skylinekids.commonweal.enums;

/**
 * 等级码
 *
 * @author MysticalDream
 */

public enum LevelCode {
    /**
     * 普通等级
     */
    COMMON_LEVEL(-1),
    /**
     * 普通登录等级
     */
    COMMON_LOGIN_LEVEL(0),
    /**
     * 特别登录等级
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
