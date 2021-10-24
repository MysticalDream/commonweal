package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.FarmerInfoDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FarmerInfo;
import team.skylinekids.commonweal.pojo.query.FarmerInfoCondition;
import team.skylinekids.commonweal.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MysticalDream
 */
public class FarmerInfoDaoImpl extends MyGenericBaseDao<FarmerInfo> implements FarmerInfoDao {

    @Override
    public Page<FarmerInfo> getFarmerInfoList(FarmerInfoCondition farmerInfoCondition) throws Exception {
        /**
         * 省份/直辖市
         */
        String province = farmerInfoCondition.getProvince();
        /**
         * 城市
         */
        String city = farmerInfoCondition.getCity();
        /**
         * 区县
         */
        String area = farmerInfoCondition.getArea();
        /**
         * 每页显示数量
         */
        Integer pageSize = farmerInfoCondition.getPageSize();
        /**
         * 第几页
         */
        Integer pageNum = farmerInfoCondition.getPageNum();

        List<String> conditionSql = new ArrayList<>(4);

        List<Object> values = new ArrayList<>(4);

        if (StringUtils.isNotBlank(province)) {
            conditionSql.add("province=?");
            values.add(province);
        }
        if (StringUtils.isNotBlank(city)) {
            conditionSql.add("city=?");
            values.add(city);
        }
        if (StringUtils.isNotBlank(area)) {
            conditionSql.add("area=?");
            values.add(area);
        }

        String sql = String.join(" AND ", conditionSql);

        if (!"".equals(sql)) {
            sql = " WHERE " + sql;
        }
        Page<FarmerInfo> page = new Page<>();

        page.setPageNum(pageNum);

        page.setPageSize(pageSize);

        Integer total = this.selectCountByCondition(sql, values);

        sql += " LIMIT " + page.getStartRow() + "," + page.getPageSize();

        List<FarmerInfo> farmerInfos = this.selectListByConditionString(sql, values);
        //设置图片地址
        farmerInfos.forEach(e -> e.setCoverUrl(ResourcePathConstant.VIRTUAL_FARMER_COVER_BASE + e.getCoverUrl()));
        /**
         * 总记录数
         */
        page.setTotal(total);
        /**
         * 数据
         */
        page.setList(farmerInfos);
        /**
         * 当前页数据数量
         */
        page.setSize(farmerInfos.size());
        //计算页数
        page.setPagesAuto();
        return page;
    }

    @Override
    public int addFarmerInfo(FarmerInfo farmerInfo) throws Exception {
        return this.insert(farmerInfo);
    }
}
