package team.skylinekids.commonweal.dao.core.annotaion;

import java.lang.annotation.*;

/**
 * 指向表中字段
 *
 * @author MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface TableField {
    /**
     * 对应的数据库表中的字段名
     */
    String value() default "";

    /**
     * 是否更新该字段
     */
    boolean update() default true;

    /**
     *是否插入该字段值
     */
    boolean insert() default true;

}
