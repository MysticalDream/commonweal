package team.skylinekids.commonweal.pojo.query;

/**
 * 团队查找条件
 *
 * @author MysticalDream
 */
public class TeamCondition extends BaseCondition {
    /**
     * 人数范围代号
     */
    private Integer numberScope;

    public TeamCondition(String province, String city, String area, Integer pageSize, Integer pageNum, Integer numberScope) {
        super(province, city, area, pageSize, pageNum);
        this.numberScope = numberScope;
    }

    public Integer getNumberScope() {
        return numberScope;
    }

    public void setNumberScope(Integer numberScope) {
        this.numberScope = numberScope;
    }
}
