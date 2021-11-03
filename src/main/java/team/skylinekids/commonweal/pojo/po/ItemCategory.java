package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.io.Serializable;

/**
 * 项目分类
 *
 * @author MysticalDream
 */
@TableName("item_category")
public class ItemCategory implements Serializable {
    /**
     * 分类ID
     */
    @TableId
    @TableField(value = "item_category_id", insert = false, update = false)
    private Integer id;
    /**
     * 分类名称
     */
    @TableField("item_category_name")
    private String categoryName;

    public ItemCategory(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public ItemCategory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
