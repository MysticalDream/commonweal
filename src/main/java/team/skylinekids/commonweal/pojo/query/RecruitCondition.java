package team.skylinekids.commonweal.pojo.query;

/**
 * 招募条件
 *
 * @author MysticalDream
 */
public class RecruitCondition extends BaseCondition {
    /**
     * 分类id
     */
    private Integer recruitCategoryId;
    /**
     * 项目分类文字
     */
    private String recruitCategory;
    /**
     * 人数范围代号
     */
    private Integer numberScope;
    /**
     * 招募状态 0---待招募 1---招募中 2---已招募完成
     */
    private Integer status;

    public RecruitCondition(String province, String city, String area, Integer pageSize, Integer pageNum, Integer recruitCategoryId, String recruitCategory, Integer numberScope, Integer status) {
        super(province, city, area, pageSize, pageNum);
        this.recruitCategoryId = recruitCategoryId;
        this.recruitCategory = recruitCategory;
        this.numberScope = numberScope;
        this.status = status;
    }

    public RecruitCondition() {

    }

    public Integer getRecruitCategoryId() {
        return recruitCategoryId;
    }

    public void setRecruitCategoryId(Integer recruitCategoryId) {
        this.recruitCategoryId = recruitCategoryId;
    }

    public String getRecruitCategory() {
        return recruitCategory;
    }

    public void setRecruitCategory(String recruitCategory) {
        this.recruitCategory = recruitCategory;
    }

    public Integer getNumberScope() {
        return numberScope;
    }

    public void setNumberScope(Integer numberScope) {
        this.numberScope = numberScope;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
