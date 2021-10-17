package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.ItemAchievement;

import java.util.List;

/**
 * 项目成就数据库操作接口
 *
 * @author MysticalDream
 */
public interface ItemAchievementDao {
    /**
     * 添加项目成就
     *
     * @param itemAchievement
     * @return
     */
    int addItemAchievement(ItemAchievement itemAchievement);

    /**
     * 更新项目成就
     *
     * @param itemAchievement
     * @return
     */
    int updateItemAchievement(ItemAchievement itemAchievement);

    /**
     * 根据项目id获取项目成就列表
     *
     * @param id 项目id
     * @return
     */
    List<ItemAchievement> getItemAchievementById(int id);



}
