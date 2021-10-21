package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.ItemAchievementDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.ItemAchievement;
import team.skylinekids.commonweal.utils.JDBCUtils;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

/**
 * 项目成就
 *
 * @author MysticalDream
 */
public class ItemAchievementDaoImpl extends MyGenericBaseDao<ItemAchievement> implements ItemAchievementDao {
    private final Logger logger = Logger.getLogger(ItemAchievementDaoImpl.class);

    @Override
    public int addItemAchievement(ItemAchievement itemAchievement) throws Exception {
        return this.insert(itemAchievement);
    }

    @Override
    public int updateItemAchievement(ItemAchievement itemAchievement) throws Exception {
        return 0;
    }

    @Override
    public List<ItemAchievement> getItemAchievementById(int id) throws Exception {
        return null;
    }

    @Override
    public int updateLoveNumber(Integer itemAchievementId, boolean status) throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String res = "";
        if (status) {
            res = "+1";
        } else {
            res = "-1";
        }
        String sql = "UPDATE " + this.getTableName() + " SET love_number=love_number" + res + " WHERE id=?";
        logger.info("===>    Preparing:" + sql);
        logger.info("===>    Parameters:[" + itemAchievementId + "]");
        return this.update(connection, sql, itemAchievementId);
    }
}
