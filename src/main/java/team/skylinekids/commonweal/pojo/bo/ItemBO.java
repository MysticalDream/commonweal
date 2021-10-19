package team.skylinekids.commonweal.pojo.bo;

import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.Item;

import java.util.List;

/**
 * 项目业务对象
 *
 * @author MysticalDream
 */
public class ItemBO {

    /**
     * 项目
     */
    private ItemDTO itemDTO;
    /**
     * 项目成员用户
     */
    private List<UserDTO> userList;
    /**
     * 团队列表
     */
    private List<TeamDTO> teamList;

    public ItemBO(ItemDTO itemDTO, List<UserDTO> userList, List<TeamDTO> teamList) {
        this.itemDTO = itemDTO;
        this.userList = userList;
        this.teamList = teamList;
    }

    public ItemBO() {
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public List<TeamDTO> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<TeamDTO> teamList) {
        this.teamList = teamList;
    }
}
