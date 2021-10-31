package team.skylinekids.commonweal.web.core.annotation;

import team.skylinekids.commonweal.enums.LevelCode;

import java.lang.annotation.*;

/**
 * 访问等级
 *
 * @author MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface AccessLevel {
    /**
     * 值越大等级越高
     *
     * @return
     */
    LevelCode value() default LevelCode.COMMON_LOGIN_LEVEL;
}
