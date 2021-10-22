package team.skylinekids.commonweal.pojo.query;

/**
 * 查询项目条件
 *
 * @author MysticalDream
 */
public class ItemCondition extends BaseCondition {
    /**
     * 分类id
     */
    private Integer itemCategoryId;
    /**
     * 项目分类文字
     */
    private String itemCategory;
    /**
     * 人数范围代号
     */
    private Integer numberScope;
    /**
     * 项目编号id
     */
    private Integer itemId;
    /**
     * 项目标题
     */
    private String itemTitle;

    public ItemCondition(String province, String city, String area, Integer pageSize, Integer pageNum, Integer itemCategoryId, String itemCategory, Integer numberScope, Integer itemId, String itemTitle) {
        super(province, city, area, pageSize, pageNum);
        this.itemCategoryId = itemCategoryId;
        this.itemCategory = itemCategory;
        this.numberScope = numberScope;
        this.itemId = itemId;
        this.itemTitle = itemTitle;
    }

    public ItemCondition() {
    }

    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getNumberScope() {
        return numberScope;
    }

    public void setNumberScope(Integer numberScope) {
        this.numberScope = numberScope;
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
}
