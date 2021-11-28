package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.LiveRoomDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.LiveRoom;

import java.util.List;

/**
 * @author MysticalDream
 */
public class LiveRoomDaoImpl extends MyGenericBaseDao<LiveRoom> implements LiveRoomDao {
    @Override
    public LiveRoom getLiveRoom(LiveRoom liveRoom) throws Exception {
        return this.selectOne(liveRoom);
    }

    @Override
    public int addLiveRoom(LiveRoom liveRoom) throws Exception {
        return this.insert(liveRoom);
    }

    @Override
    public int updateLiveRoom(LiveRoom liveRoom) throws Exception {
        return this.update(liveRoom);
    }

    @Override
    public List<LiveRoom> getLiveRooms() throws Exception {
        return this.selectAll();
    }

    @Override
    public List<LiveRoom> getListByPagination(String conditionSql, Page<LiveRoom> page) throws Exception {
        return null;
    }
}
