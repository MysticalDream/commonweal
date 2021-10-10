package team.skylinekids.commonweal.dao.core.annotaion;

import java.lang.annotation.*;

/**
 * 表名
 *
 * @author MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface TableName {
    /**
     * 数据库中对应的表名
     */
    String value() default "";
}
