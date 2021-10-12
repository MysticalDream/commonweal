package team.skylinekids.commonweal.web.core.annotation;

import java.lang.annotation.*;

/**
 * 标记需要登录的注解
 *
 * @author MysticalDream
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireLogin {

}
