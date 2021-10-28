package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.AchievementVODao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.AchievementVO;
import team.skylinekids.commonweal.utils.JDBCUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author MysticalDream
 */
public class AchievementVODaoImpl extends MyGenericBaseDao<AchievementVO> implements AchievementVODao {
    private final Logger logger = Logger.getLogger(AchievementVODaoImpl.class);

    @Override
    public Page<AchievementVO> getAchievementVOList(Page<AchievementVO> page, boolean sort, Integer userId) throws Exception {
        String s = sort ? "ASC" : "DESC";
        //条件语句
        String sqlCondition = " ORDER BY gmt_create " + s + " LIMIT " + page.getStartRow() + "," + page.getPageSize();
        //总数
        Integer count = this.selectAllCount();
        String sql = "SELECT a.id as id,a.cover_url as coverUrl,a.introduction as introduction,a.type as type,a.type_id as typeId,a.gmt_create as gmtCreate,a.gmt_modified as gmtModified,a.love_number as loveNumber,a.title as title,b.`status` as loved  FROM commonweal.achievement a LEFT JOIN commonweal.thumb b ON a.id = b.achievement_id  AND b.user_id = ?" + sqlCondition;
        logger.info("===>    Preparing:" + sql);
        logger.info("===>    Parameters:" + "[" + userId + "]");
        List<AchievementVO> achievements = this.getListBean(JDBCUtils.getConnection(), sql, userId);
        for (AchievementVO achievement :
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
}
