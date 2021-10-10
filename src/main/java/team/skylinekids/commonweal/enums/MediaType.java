package team.skylinekids.commonweal.enums;

/**
 * MediaType类型
 *
 * @author MysticalDream
 */

public interface MediaType {
    /**
     * 原生表单
     */
    String APPLICATION_FORM_URLENCODED_TYPE = "application/x-www-form-urlencoded";
    /**
     * json
     */
    String APPLICATION_JSON = "application/json";
    /**
     * 多媒体数据
     */
    String MULTIPART_FORM_DATA = "multipart/form-data";
    /**
     * 数据流
     */
    String MULTIPART_MIXED = "multipart/mixed";

}
