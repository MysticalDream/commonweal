package team.skylinekids.commonweal.pojo.dto;

import java.util.Arrays;

/**
 * 反馈
 *
 * @author MysticalDream
 */
public class FeedbackDTO {

    private Integer adoptId;
    private String content;
    private String[] pics;

    public FeedbackDTO(Integer adoptId, String content, String[] pics) {
        this.adoptId = adoptId;
        this.content = content;
        this.pics = pics;
    }

    public FeedbackDTO() {
    }

    public Integer getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(Integer adoptId) {
        this.adoptId = adoptId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "adoptId=" + adoptId +
                ", content='" + content + '\'' +
                ", pics=" + Arrays.toString(pics) +
                '}';
    }
}
