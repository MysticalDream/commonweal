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
    /**
     * 团队编号
     */
    private Integer teamId;
    /**
     * 队伍名称
     */
    private String teamName;

    public TeamCondition(String province, String city, String area, Integer pageSize, Integer pageNum, Integer numberScope, Integer teamId, String teamName) {
        super(province, city, area, pageSize, pageNum);
        this.numberScope = numberScope;
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public TeamCondition() {
    }

    public Integer getNumberScope() {
        return numberScope;
    }

    public void setNumberScope(Integer numberScope) {
        this.numberScope = numberScope;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
