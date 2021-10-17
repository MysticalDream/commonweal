package team.skylinekids.commonweal.enums;

/**
 * 人数范围
 *
 * @author MysticalDream
 */

public enum NumberScope {
    /**
     * 范围 0-20
     */
    SCOPE1(1, 0, 20),
    /**
     * 范围 21-40
     */
    SCOPE2(2, 21, 40),
    /**
     * 范围 41-60
     */
    SCOPE3(3, 41, 60),
    /**
     * 范围 61-80
     */
    SCOPE4(4, 61, 80),
    /**
     * 范围 81-100
     */
    SCOPE5(5, 81, 100),
    /**
     * 范围 100以上
     */
    SCOPE6(6, null, 100);

    final String scope;

    int code;

    NumberScope(int code, Integer begin, Integer end) {
        this.code = code;
        if (begin == null) {
            scope = " max_men>" + end + " ";
        } else {
            if (end == 20) {
                scope = " max_men<=20 ";
            } else {
                scope = " max_men>=" + begin + " AND max_men<=" + end + " ";
            }
        }
    }

    public String getScope() {
        return scope;
    }

    public int getCode() {
        return code;
    }
}
