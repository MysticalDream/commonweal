package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 直播间
 *
 * @author MysticalDream
 */
@TableName("live_room")
public class LiveRoom {
    /**
     * id
     */
    @TableId
    @TableField(value = "live_id", update = false, insert = false)
    private Integer liveId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 房间号码
     */
    private String uuid;
    /**
     * 直播封面
     */
    private String coverUrl;
    /**
     * 直播标题
     */
    private String title;
    /**
     * 直播简介
     */
    private String intro;
    /**
     * 分类id
     */
    private Integer categoryId;

    public LiveRoom(Integer liveId, Integer userId, LocalDateTime gmtCreate, LocalDateTime gmtModified, String uuid, String coverUrl, String title, String intro, Integer categoryId) {
        this.liveId = liveId;
        this.userId = userId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.uuid = uuid;
        this.coverUrl = coverUrl;
        this.title = title;
        this.intro = intro;
        this.categoryId = categoryId;
    }

    public LiveRoom() {
    }

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
