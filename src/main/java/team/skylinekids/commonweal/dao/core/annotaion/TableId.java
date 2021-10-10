package team.skylinekids.commonweal.dao.core.annotaion;

import java.lang.annotation.*;

/**
 * 定义主键id
 * @author MysticalDream
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableId {

}
