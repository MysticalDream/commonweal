package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 团队积分
 *
 * @author MysticalDream
 */
@TableName("team_score")
public class TeamScore {
    /**
     * 自增id
     */
    @TableId
    @TableField(value = "score_id", insert = false, update = false)
    private Integer scoreId;
    /**
     * 团队id
     */
    private Integer teamId;
    /**
     * 积分数
     */
    private Integer score;
    /**
     * 积分来源 1-发布成就 2-参与项目
     */
    private Integer scoreSrc;
    /**
     * 积分来源id 发布的成就id或者加入的项目id
     */
    private Integer srcId;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    public TeamScore(Integer scoreId, Integer teamId, Integer score, Integer scoreSrc, Integer srcId, LocalDateTime gmtCreate, LocalDateTime gmtModified) {
        this.scoreId = scoreId;
        this.teamId = teamId;
        this.score = score;
        this.scoreSrc = scoreSrc;
        this.srcId = srcId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public TeamScore() {
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScoreSrc() {
        return scoreSrc;
    }

    public void setScoreSrc(Integer scoreSrc) {
        this.scoreSrc = scoreSrc;
    }

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
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
}
