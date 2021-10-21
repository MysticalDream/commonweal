package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.RecruitDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.RecruitDTO;
import team.skylinekids.commonweal.pojo.po.Recruit;
import team.skylinekids.commonweal.pojo.query.RecruitCondition;
import team.skylinekids.commonweal.utils.ResourceURLUtils;
import team.skylinekids.commonweal.utils.ScopeUtils;
import team.skylinekids.commonweal.utils.StringUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 招募志愿者信息
 *
 * @author MysticalDream
 */
public class RecruitDaoImpl extends MyGenericBaseDao<Recruit> implements RecruitDao {

    @Override
    public int addRecruit(Recruit recruit) throws Exception {
        return this.insert(recruit);
    }

    @Override
    public Page<RecruitDTO> getByConditionString(RecruitCondition recruitCondition) throws Exception {
        /**
         * 省份/直辖市
         */
        String province = recruitCondition.getProvince();
        /**
         * 城市
         */
        String city = recruitCondition.getCity();
        /**
         * 区县
         */
        String area = recruitCondition.getArea();
        /**
         * 分类id
         */
        Integer categoryId = recruitCondition.getRecruitCategoryId();
        /**
         * 人数范围
         */
        Integer numberScope = recruitCondition.getNumberScope();
        /**
         * 招募状态
         */
        Integer status = recruitCondition.getStatus();

        /**
         * 每页显示数量
         */
        Integer pageSize = recruitCondition.getPageSize();
        /**
         * 第几页
         */
        Integer pageNum = recruitCondition.getPageNum();

        List<String> conditionSql = new ArrayList<>(6);

        List<Object> values = new ArrayList<>(6);

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
        if (categoryId != null) {
            conditionSql.add("category_id=?");
            values.add(categoryId);
        }

        String scope = ScopeUtils.getScopeByNum(numberScope);

        if (scope != null) {
            conditionSql.add(scope);
        }
        if (status != null) {
            /**
             * 开始
             */
            String timeStart = "gmt_start";
            /**
             * 结束
             */
            String timeEnd = "gmt_end";
            /**
             * 招募状态
             */
            switch (status) {
                //待招募
                case 0:
                    conditionSql.add(timeStart + ">CURRENT_TIMESTAMP");
                    break;
                //招募中
                case 1:
                    conditionSql.add(timeStart + "<=CURRENT_TIMESTAMP" + " AND " + timeEnd + ">CURRENT_TIMESTAMP");
                    break;
                //招募已完成
                case 2:
                    conditionSql.add(timeEnd + "<CURRENT_TIMESTAMP");
                    break;
                default:
            }
        }


        String sql = String.join(" AND ", conditionSql);

        if (!"".equals(sql)) {
            sql = " WHERE " + sql;
        }
        Page<RecruitDTO> page = new Page<>();

        page.setPageNum(pageNum);

        page.setPageSize(pageSize);

        Integer total = this.selectCountByCondition(sql, values);

        sql += " LIMIT " + page.getStartRow() + "," + page.getPageSize();

        List<Recruit> recruits = this.selectListByConditionString(sql, values);

        List<RecruitDTO> recruitDTOS = ConversionUtils.convertList(recruits, RecruitDTO.class);
        //设置图片
        ResourceURLUtils.setRecruitsURL(recruitDTOS);
        /**
         * 总记录数
         */
        page.setTotal(total);
        /**
         * 数据
         */
        page.setList(recruitDTOS);
        /**
         * 当前页数据数量
         */
        page.setSize(recruitDTOS.size());
        //计算页数
        page.setPagesAuto();

        return page;
    }
}
