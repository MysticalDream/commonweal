package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.LiveRoomDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.LiveRoom;
import team.skylinekids.commonweal.service.LiveService;
import team.skylinekids.commonweal.utils.UUIDUtils;

/**
 * 直播间服务
 *
 * @author MysticalDream
 */
public class LiveServiceImpl implements LiveService {
    LiveRoomDao liveRoomDao = DaoFactory.getLiveRoomDao();


    @Override
    public LiveRoom getLiveRoom(LiveRoom liveRoom) throws Exception {
        return liveRoomDao.getLiveRoom(liveRoom);
    }

    @Override
    public int addLiveRoom(LiveRoom liveRoom) throws Exception {
        return liveRoomDao.addLiveRoom(liveRoom);
    }

    @Override
    public int updateLiveRoom(LiveRoom liveRoom) throws Exception {
        return liveRoomDao.updateLiveRoom(liveRoom);
    }

    @Override
    public void handleLiveRoomInfo(LiveRoom liveRoom) throws Exception {
        LiveRoom room = this.getLiveRoom(liveRoom);
        if (room == null) {
            String uuid = UUIDUtils.getPureUUID();
            liveRoom.setUuid(uuid);
            this.addLiveRoom(liveRoom);
        } else {
            liveRoom.setLiveId(room.getLiveId());
            this.updateLiveRoom(liveRoom);
        }
    }
}
