package team.skylinekids.commonweal.utils;

import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.dto.RecruitDTO;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.dto.UserDTO;

import java.util.List;

/**
 * 图片资源路径工具
 *
 * @author MysticalDream
 */
public final class ResourceURLUtils {

    private ResourceURLUtils() {

    }

    /**
     * 项目封面url
     *
     * @param list
     */
    public static void setItemsURL(List<ItemDTO> list) {
        for (ItemDTO itemDTO :
                list) {
            itemDTO.setCoverUrl(ResourcePathConstant.VIRTUAL_ITEM_COVER_BASE + itemDTO.getCoverUrl());
        }
    }

    /**
     * 设置用户头像路径
     *
     * @param list
     */
    public static void setUsersURL(List<UserDTO> list) {
        for (UserDTO userDTO :
                list) {
            userDTO.setAvatarUrl(ResourcePathConstant.VIRTUAL_USER_AVATAR_URL_BASE + userDTO.getAvatarUrl());
        }
    }

    /**
     * 设置团队头像路径
     *
     * @param list
     */
    public static void setTeamsURL(List<TeamDTO> list) {
        for (TeamDTO teamDTO :
                list) {
            teamDTO.setTeamAvatar(ResourcePathConstant.VIRTUAL_TEAM_COVER_BASE + teamDTO.getTeamAvatar());
        }
    }

    /**
     * 设置招募志愿图片
     *
     * @param list
     */
    public static void setRecruitsURL(List<RecruitDTO> list) {
        for (RecruitDTO recruitDTO :
                list) {
            recruitDTO.setCoverUrl(ResourcePathConstant.VIRTUAL_RECRUIT_COVER_BASE + recruitDTO.getCoverUrl());
        }
    }

}
