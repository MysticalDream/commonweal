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


    public ItemCondition(String province, String city, String area, Integer pageSize, Integer pageNum, Integer itemCategoryId, String ItemCategory, Integer numberScope) {
        super(province, city, area, pageSize, pageNum);
        this.itemCategoryId = itemCategoryId;
        this.itemCategory = ItemCategory;
        this.numberScope = numberScope;
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

}
