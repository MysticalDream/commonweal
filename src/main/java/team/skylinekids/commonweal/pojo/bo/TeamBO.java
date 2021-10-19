package team.skylinekids.commonweal.pojo.bo;

import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.User;

import java.util.List;

/**
 * @author MysticalDream
 */
public class TeamBO {
    /**
     * 团队
     */
    private TeamDTO teamDTO;
    /**
     * 队员列表
     */
    private List<User> userList;

    public TeamBO(TeamDTO teamDTO, List<User> userList) {
        this.teamDTO = teamDTO;
        this.userList = userList;
    }

    public TeamDTO getTeamDTO() {
        return teamDTO;
    }

    public void setTeamDTO(TeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
