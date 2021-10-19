package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.pojo.query.TeamCondition;
import team.skylinekids.commonweal.utils.JDBCUtils;
import team.skylinekids.commonweal.utils.ScopeUtils;
import team.skylinekids.commonweal.utils.SqlUtils;
import team.skylinekids.commonweal.utils.StringUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.utils.reflect.ReflectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MysticalDream
 */
public class TeamDaoImpl extends MyGenericBaseDao<Team> implements TeamDao {

    private final Logger logger = Logger.getLogger(TeamDaoImpl.class);

    @Override
    public int addTeam(Team team) throws Exception {
        return this.insert(team);
    }

    @Override
    public Team getTeamById(int id) throws Exception {
        return this.selectByPrimaryKey(id);
    }

    @Override
    public int updateTeam(Team team) throws Exception {
        return this.update(team);
    }

    @Override
    public List<Team> getItemTeamList(Integer itemId) throws Exception {
        String sql = "SELECT " + SqlUtils.getSelectColumnsByField(ReflectUtils.getAllFields(Team.class), true) + " FROM " + this.getTableName() + " WHERE team_id IN (SELECT target_id FROM item_and_member_map WHERE item_id=? AND type=? AND is_available=?)";
        logger.info("===>   Preparing:" + sql);
        logger.info("===>   Parameters:" + "[" + itemId + ",false,true]");
        List<Team> teams = this.getListBean(JDBCUtils.getConnection(), sql, itemId, true, true);
        return teams;
    }

    @Override
    public Page<TeamDTO> getTeamByCondition(TeamCondition teamCondition) throws Exception {
        /**
         * 省份
         */
        String province = teamCondition.getProvince();
        /**
         * 城市
         */
        String city = teamCondition.getCity();
        /**
         * 地区
         */
        String area = teamCondition.getArea();
        /**
         * 人数范围
         */
        Integer numberScope = teamCondition.getNumberScope();
        /**
         * 每页显示数量
         */
        Integer pageSize = teamCondition.getPageSize();
        /**
         * 第几页
         */
        Integer pageNum = teamCondition.getPageNum();

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
        String scope = ScopeUtils.getScopeByNum(numberScope);

        if (scope != null) {
            conditionSql.add(scope);
        }
        String sql = String.join(" AND ", conditionSql);

        if (!"".equals(sql)) {
            sql = " WHERE " + sql;
        }
        Page<TeamDTO> page = new Page<>();
        page.setPageNum(pageNum);

        page.setPageSize(pageSize);

        Integer total = this.selectCountByCondition(sql, values);
        sql += " LIMIT " + page.getStartRow() + "," + page.getPageSize();

        List<Team> teams = this.selectListByConditionString(sql, values);

        List<TeamDTO> teamDTOS = ConversionUtils.convertList(teams, TeamDTO.class);
        /**
         * 总记录数
         */
        page.setTotal(total);
        /**
         * 数据
         */
        page.setList(teamDTOS);
        /**
         * 当前页数据数量
         */
        page.setSize(teamDTOS.size());
        //计算页数
        page.setPagesAuto();

        return page;

    }
}
