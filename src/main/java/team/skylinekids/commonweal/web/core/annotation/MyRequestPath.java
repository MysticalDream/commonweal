package team.skylinekids.commonweal.web.core.annotation;

import team.skylinekids.commonweal.enums.RequestType;

import java.lang.annotation.*;

/**
 * 请求路径映射
 *
 * @author MysticalDream
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestPath {
    /**
     * Controller或其方法所处理的请求的访问路径
     *
     * @return
     */
    String value() default "";

    /**
     * Controller或其方法所处理的请求的访问路径
     * 等价于value
     *
     * @return
     */
    String urlPattern() default "";

    /**
     * 请求方式，默认4种都支持
     *
     * @return
     */
    RequestType[] type() default {RequestType.GET, RequestType.POST, RequestType.PUT, RequestType.DELETE};


}
