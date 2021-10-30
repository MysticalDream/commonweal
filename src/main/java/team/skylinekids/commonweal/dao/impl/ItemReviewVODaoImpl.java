package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.ItemReviewVO;
import team.skylinekids.commonweal.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author MysticalDream
 */
public class ItemReviewVODaoImpl extends MyGenericBaseDao<ItemReviewVO> implements team.skylinekids.commonweal.dao.ItemReviewVODao {

    private final Logger logger = Logger.getLogger(ItemReviewVODaoImpl.class);

    @Override
    public Page<ItemReviewVO> getList(Page<ItemReviewVO> page, Integer itemId) throws Exception {

        String sqlCount = "SELECT count(*) FROM commonweal.item_and_member_map a left join commonweal.`user` b on a.target_id = b.user_id LEFT JOIN commonweal.team c on a.target_id = c.team_id where a.item_id = ? AND a.`status` = 0 ;";

        String sqlSelect = "SELECT a.id listId, a.type,(case a.type when 0 then'队伍'else '个人' end) as typeName, a.target_id as targetId, ( CASE a.type WHEN 1 THEN b.username ELSE c.team_name END) as `name`, ( case a.type when 1 then b.location else ( SELECT `name` FROM commonweal.province where province = c.province AND city = '0' ) end ) as location FROM commonweal.item_and_member_map a left join commonweal.`user` b on a.target_id = b.user_id LEFT JOIN commonweal.team c on a.target_id = c.team_id where a.item_id = ? AND a.`status` = 0 ORDER BY a.gmt_create desc LIMIT ?,?;";

        Connection connection = JDBCUtils.getConnection();

        logger.info("===>   Preparing:" + sqlCount);

        logger.info("===>    Parameters:" + "[" + itemId + "]");
        //获取总数
        int count = ((Number) this.getSingleValue(connection, sqlCount, itemId)).intValue();

        logger.info("===>   Preparing:" + sqlSelect);

        logger.info("===>    Parameters:" + "[" + itemId + "," + page.getStartRow() + "," + page.getPageSize() + "]");
        //获取审核列表
        List<ItemReviewVO> itemReviewVOS = this.getListBean(connection, sqlSelect, itemId, page.getStartRow(), page.getPageSize());
        //总数
        page.setTotal(count);
        //当前页数量
        page.setSize(itemReviewVOS.size());
        //列表
        page.setList(itemReviewVOS);
        //页数
        page.setPagesAuto();
        return page;
    }
}
