package team.skylinekids.commonweal.pojo.po;

import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.dao.core.annotaion.TableName;

import java.time.LocalDateTime;

/**
 * 反馈资源
 *
 * @author MysticalDream
 */
@TableName("adopt_feedback_resource")
public class FeedbackResource {
    /**
     * 资源id
     */
    @TableId
    @TableField(value = "src_id", insert = false, update = false)
    private Integer srcId;
    /**
     * 资源名
     */
    private String srcName;
    /**
     * 反馈id
     */
    private Integer feedbackId;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    public FeedbackResource(Integer srcId, String srcName, Integer feedbackId, LocalDateTime gmtCreate, LocalDateTime gmtModified) {
        this.srcId = srcId;
        this.srcName = srcName;
        this.feedbackId = feedbackId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public FeedbackResource() {

    }

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
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
