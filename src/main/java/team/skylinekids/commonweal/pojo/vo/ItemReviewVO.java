package team.skylinekids.commonweal.pojo.vo;

import java.time.LocalDateTime;

/**
 * 审核项目视图
 *
 * @author MysticalDream
 */
public class ItemReviewVO {
    /**
     * 项目id
     */
    private Integer itemId;
    /**
     * 项目标题
     */
    private String itemTitle;
    /**
     * 项目创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 用户名
     */
    private String username;

    public ItemReviewVO(Integer itemId, String itemTitle, LocalDateTime gmtCreate, String username) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.gmtCreate = gmtCreate;
        this.username = username;
    }

    public ItemReviewVO() {

    }

    public ItemReviewVO(Integer itemId, String itemTitle, LocalDateTime gmtCreate) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.gmtCreate = gmtCreate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
