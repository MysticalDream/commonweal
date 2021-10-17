package team.skylinekids.commonweal.utils.reflect;

import java.lang.annotation.*;

/**
 * 忽略收集
 *
 * @author MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface IgnoreCollection {
}
