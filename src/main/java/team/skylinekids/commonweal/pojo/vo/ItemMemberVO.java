package team.skylinekids.commonweal.pojo.vo;

/**
 * 审核列表
 *
 * @author MysticalDream
 */
public class ItemMemberVO {
    /**
     * 列表id
     */
    private Integer listId;
    /**
     * 成员类型
     */
    private Boolean type;
    /**
     * 类型名
     */
    private String typeName;
    /**
     * 目标id
     */
    private Integer targetId;
    /**
     * 名字
     */
    private String name;
    /**
     * 位置
     */
    private String location;

    public ItemMemberVO(Integer listId, Boolean type, String typeName, Integer targetId, String name, String location) {
        this.listId = listId;
        this.type = type;
        this.typeName = typeName;
        this.targetId = targetId;
        this.name = name;
        this.location = location;
    }

    public ItemMemberVO() {
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ItemReviewVO{" +
                "listId=" + listId +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", targetId=" + targetId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
