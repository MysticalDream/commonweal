package team.skylinekids.commonweal.pojo.bo;

import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.FeedbackResource;

import java.util.List;

/**
 * 评论信息
 *
 * @author MysticalDream
 */
public class CommentInfo {

    private Object comment;

    private UserDTO userDTO;

    private List<FeedbackResource> picList;


    public CommentInfo(Object comment, UserDTO userDTO) {
        this.comment = comment;
        this.userDTO = userDTO;
    }

    public CommentInfo() {

    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<FeedbackResource> getPicList() {
        return picList;
    }

    public void setPicList(List<FeedbackResource> picList) {
        this.picList = picList;
    }
}
