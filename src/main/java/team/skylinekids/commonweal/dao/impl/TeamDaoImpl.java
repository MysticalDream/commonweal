package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.query.TeamCondition;
import team.skylinekids.commonweal.utils.*;
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
    public int updateNowMen(Integer teamId, Integer number) throws Exception {
        String updateSql = "UPDATE " + this.getTableName() + " SET now_men=now_men+" + number + " WHERE team_id=?";
        return this.update(JDBCUtils.getConnection(), updateSql, teamId);
    }

    @Override
    public List<Team> getItemTeamList(Integer itemId) throws Exception {
        String sql = "SELECT " + SqlUtils.getSelectColumnsByField(ReflectUtils.getAllFields(Team.class), true) + " FROM " + this.getTableName() + " WHERE team_id IN (SELECT target_id FROM item_and_member_map WHERE item_id=? AND type=? AND is_available=? AND status=?)";
        logger.info("===>   Preparing:" + sql);
        logger.info("===>   Parameters:" + "[" + itemId + ",false,true,1]");
        List<Team> teams = this.getListBean(JDBCUtils.getConnection(), sql, itemId, false, true, 1);
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
         * 团队编号
         */
        Integer teamId = teamCondition.getTeamId();
        /**
         * 团队名称
         */
        String teamName = teamCondition.getTeamName();
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
        RecruitDaoImpl.handle(province, city, area, conditionSql, values);
        if (numberScope != null) {
            String scope = ScopeUtils.getScopeByNum(numberScope);

            if (scope != null) {
                conditionSql.add(scope);
            }
        }
        if (teamId != null) {
            conditionSql.add("team_id=?");
            values.add(teamId);
        }
        if (StringUtils.isNotBlank(teamName)) {
            conditionSql.add("team_name like ?");
            values.add("%" + teamName + "%");
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
        ResourceURLUtils.setTeamsURL(teamDTOS);
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

    @Override
    public Page<TeamDTO> getTeamsByUserId(Page<TeamDTO> page, Integer userId) throws Exception {
        String sqlCondition = " WHERE user_id=" + userId;
        Integer integer = this.selectCountByCondition(sqlCondition, new ArrayList<>());
        List<Team> teams = this.selectListByConditionString(sqlCondition + " LIMIT " + page.getStartRow() + "," + page.getPageSize(), new ArrayList<>());
        page.setTotal(integer);
        List<TeamDTO> teamDTOS = ConversionUtils.convertList(teams, TeamDTO.class);
        for (TeamDTO teamDTO :
                teamDTOS) {
            teamDTO.setTeamAvatar(ResourcePathConstant.VIRTUAL_TEAM_COVER_BASE + teamDTO.getTeamAvatar());
        }
        page.setSize(teamDTOS.size());
        page.setList(teamDTOS);
        page.setPagesAuto();
        return page;
    }

    @Override
    public Page<TeamDTO> getUserJoinedTeam(Page<TeamDTO> page, Integer userId) throws Exception {
        String sql = " WHERE team_id IN(SELECT team_id FROM team_and_user_map WHERE user_id=? AND status=1)";
        Integer count = this.selectCountByCondition(sql, List.of(userId));
        page.setTotal(count);
        List<Team> teams = this.selectListByConditionString(sql + " LIMIT " + page.getStartRow() + "," + page.getPageSize(), List.of(userId));
        List<TeamDTO> teamDTOS = ConversionUtils.convertList(teams, team.skylinekids.commonweal.pojo.dto.TeamDTO.class);
        for (TeamDTO teamDTO :
                teamDTOS) {
            teamDTO.setTeamAvatar(ResourcePathConstant.VIRTUAL_TEAM_COVER_BASE + teamDTO.getTeamAvatar());
        }
        page.setList(teamDTOS);
        page.setSize(teamDTOS.size());
        page.setPagesAuto();
        return page;
    }

}
