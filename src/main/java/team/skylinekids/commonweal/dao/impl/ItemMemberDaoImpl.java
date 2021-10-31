package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.ItemMemberDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.ItemMemberVO;
import team.skylinekids.commonweal.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author MysticalDream
 */
public class ItemMemberDaoImpl extends MyGenericBaseDao<ItemMemberVO> implements ItemMemberDao {

    private final Logger logger = Logger.getLogger(ItemMemberDaoImpl.class);

    @Override
    public Page<ItemMemberVO> getList(Page<ItemMemberVO> page, Integer itemId) throws Exception {

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
        List<ItemMemberVO> itemMemberVOS = this.getListBean(connection, sqlSelect, itemId, page.getStartRow(), page.getPageSize());
        //总数
        page.setTotal(count);
        //当前页数量
        page.setSize(itemMemberVOS.size());
        //列表
        page.setList(itemMemberVOS);
        //页数
        page.setPagesAuto();
        return page;
    }

    @Override
    public Page<ItemMemberVO> getMemberList(Page<ItemMemberVO> page, Integer itemId) throws Exception {
        String sqlCount = "SELECT count(*) FROM commonweal.item_and_member_map a left join commonweal.`user` b on a.target_id = b.user_id LEFT JOIN commonweal.team c on a.target_id = c.team_id where a.item_id = ? AND a.`status` = 1 ;";

        String sqlSelect = "SELECT a.id listId, a.type,(case a.type when 0 then'队伍'else '个人' end) as typeName, a.target_id as targetId, ( CASE a.type WHEN 1 THEN b.username ELSE c.team_name END) as `name`, ( case a.type when 1 then b.location else ( SELECT `name` FROM commonweal.province where province = c.province AND city = '0' ) end ) as location FROM commonweal.item_and_member_map a left join commonweal.`user` b on a.target_id = b.user_id LEFT JOIN commonweal.team c on a.target_id = c.team_id where a.item_id = ? AND a.`status` = 1 AND a.`is_available`=1 ORDER BY a.gmt_create desc LIMIT ?,?;";

        Connection connection = JDBCUtils.getConnection();

        logger.info("===>   Preparing:" + sqlCount);

        logger.info("===>    Parameters:" + "[" + itemId + "]");
        //获取总数
        int count = ((Number) this.getSingleValue(connection, sqlCount, itemId)).intValue();

        logger.info("===>   Preparing:" + sqlSelect);

        logger.info("===>    Parameters:" + "[" + itemId + "," + page.getStartRow() + "," + page.getPageSize() + "]");
        //获取审核列表
        List<ItemMemberVO> itemMemberVOS = this.getListBean(connection, sqlSelect, itemId, page.getStartRow(), page.getPageSize());
        //总数
        page.setTotal(count);
        //当前页数量
        page.setSize(itemMemberVOS.size());
        //列表
        page.setList(itemMemberVOS);
        //页数
        page.setPagesAuto();
        return page;
    }
}
