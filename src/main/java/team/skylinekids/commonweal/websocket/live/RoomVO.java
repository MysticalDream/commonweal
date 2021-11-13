package team.skylinekids.commonweal.websocket.live;

import team.skylinekids.commonweal.pojo.dto.UserDTO;

/**
 * 直播间
 *
 * @author MysticalDream
 */
public class RoomVO {
    /**
     * 直播创建者
     */
    private UserDTO userDTO;
    /**
     * 房间id
     */
    private String uuid;
    /**
     * 直播标题
     */
    private String title;
    /**
     * 直播封面
     */
    private String coverUrl;
    /**
     * 直播介绍
     */
    private String intro;
    /**
     * 分类id
     */
    private Integer categoryId;

    public RoomVO(UserDTO userDTO, String uuid, String title, String coverUrl, String intro, Integer categoryId) {
        this.userDTO = userDTO;
        this.uuid = uuid;
        this.title = title;
        this.coverUrl = coverUrl;
        this.intro = intro;
        this.categoryId = categoryId;
    }

    public RoomVO() {

    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
