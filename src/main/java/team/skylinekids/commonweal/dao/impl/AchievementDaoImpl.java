package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.AchievementDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Achievement;
import team.skylinekids.commonweal.utils.JDBCUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MysticalDream
 */
public class AchievementDaoImpl extends MyGenericBaseDao<Achievement> implements AchievementDao {
    private final Logger logger = Logger.getLogger(AchievementDaoImpl.class);

    @Override
    public int addAchievement(Achievement achievement) throws Exception {
        return this.insert(achievement);
    }

    @Override
    public int updateAchievement(Achievement achievement) throws Exception {
        return 0;
    }

    @Override
    public List<Achievement> getAchievementList(Achievement achievement) throws Exception {
        return this.selectList(achievement);
    }

    @Override
    public Page<Achievement> getAchievementByLimit(Page<Achievement> page, boolean sort) throws Exception {
        String s = sort ? "ASC" : "DESC";
        //条件语句
        String sqlCondition = " ORDER BY gmt_create " + s + " LIMIT " + page.getStartRow() + "," + page.getPageSize();
        //总数
        Integer count = this.selectAllCount();
        List<Achievement> achievements = this.selectListByConditionString(sqlCondition, new ArrayList<>());
        for (Achievement achievement :
                achievements) {
            achievement.setCoverUrl(ResourcePathConstant.VIRTUAL_ACHIEVEMENT_IMG_BASE + achievement.getCoverUrl());
        }
        //总数
        page.setTotal(count);
        //设置列表
        page.setList(achievements);
        //设置当前数据大小
        page.setSize(achievements.size());
        //设置总页数
        page.setPagesAuto();
        return page;
    }

    @Override
    public int updateLoveNumber(Achievement achievement, boolean status) throws Exception {
        Connection connection = JDBCUtils.getConnection();

        String res;

        if (status) {
            res = "+1";
        } else {
            res = "-1";
        }
        String sql = "UPDATE " + this.getTableName() + " SET love_number=love_number" + res + " WHERE id=?";
        logger.info("===>    Preparing:" + sql);
        logger.info("===>    Parameters:[" + achievement.getId() + "]");
        return this.update(connection, sql, achievement.getId());
    }

}
