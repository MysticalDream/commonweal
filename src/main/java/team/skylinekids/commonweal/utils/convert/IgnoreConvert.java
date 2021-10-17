package team.skylinekids.commonweal.utils.convert;

import java.lang.annotation.*;

/**
 * 忽略该字段转化
 *
 * @author MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface IgnoreConvert {
}
