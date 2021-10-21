package team.skylinekids.commonweal.pojo.bo;

import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.dto.UserDTO;

import java.util.List;

/**
 * @author MysticalDream
 */
public class TeamBO {
    /**
     * 团队
     */
    private TeamDTO team;
    /**
     * 队员列表
     */
    private List<UserDTO> userList;

    public TeamBO(TeamDTO team, List<UserDTO> userList) {
        this.team = team;
        this.userList = userList;
    }

    public TeamBO() {
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }
}
