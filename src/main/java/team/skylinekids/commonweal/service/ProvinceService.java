package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.Province;

/**
 * @author MysticalDream
 */
public interface ProvinceService {
    /**
     * 根据省份条件查询获取名字
     *
     * @param province
     * @return
     * @throws Exception
     */
    String getCityNameByCondition(Province province) throws Exception;

    /**
     * 获取省份信息根据province条件
     *
     * @param province
     * @return
     * @throws Exception
     */
    Province getProvinceByCondition(Province province) throws Exception;
}
