package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.Province;

/**
 * @author MysticalDream
 */
public interface ProvinceDao {
    /**
     * 获取城市名称
     *
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
