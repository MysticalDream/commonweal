package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.LiveRoomDao;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.LiveRoom;
import team.skylinekids.commonweal.service.LiveService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
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
        LiveRoom roomCondition = new LiveRoom();
        roomCondition.setUserId(liveRoom.getUserId());
        LiveRoom room = this.getLiveRoom(roomCondition);
        String coverUrl = liveRoom.getCoverUrl();
        String fileName = FileUtils.getFileName(coverUrl);
        liveRoom.setCoverUrl(fileName);
        //uuid不可设置
        liveRoom.setUuid(null);
        if (room == null) {
            String uuid = UUIDUtils.getPureUUID();
            //把封面从暂存区放到真正的目录中
            if (!FileUtils.cutFile(ResourcePathConstant.DISK_LIVE_TEMP_BASE + fileName, ResourcePathConstant.DISK_LIVE_IMG_BASE + fileName)) {
                throw new Exception("文件保存出错");
            }
            liveRoom.setUuid(uuid);
            this.addLiveRoom(liveRoom);
        } else {
            if (!room.getCoverUrl().equals(liveRoom.getCoverUrl())) {
                //把封面从暂存区放到真正的目录中
                if (!FileUtils.cutFile(ResourcePathConstant.DISK_LIVE_TEMP_BASE + fileName, ResourcePathConstant.DISK_LIVE_IMG_BASE + fileName)) {
                    throw new Exception("文件保存出错");
                }
            }
            liveRoom.setLiveId(room.getLiveId());
            liveRoom.setUuid(room.getUuid());
            this.updateLiveRoom(liveRoom);
        }
    }
}
