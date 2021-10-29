package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.ItemBO;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.ItemBOService;
import team.skylinekids.commonweal.utils.CategoryUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;

import java.util.List;

/**
 * @author MysticalDream
 */
public class ItemBOServiceImpl implements ItemBOService {

    ItemDao itemDao = DaoFactory.getItemDao();
    UserDao userDao = DaoFactory.getUserDao();
    TeamDao teamDao = DaoFactory.getTeamDao();

    @Override
    public ItemBO getItemBOByItemId(Integer itemId) throws Exception {

        Item item = itemDao.getItemById(itemId);

        ItemDTO itemDTO = ConversionUtils.convert(item, ItemDTO.class);
        itemDTO.setItemCategory(CategoryUtils.getCategoryNameById(item.getItemCategoryId()));
        itemDTO.setCoverUrl(ResourcePathConstant.VIRTUAL_ITEM_COVER_BASE + itemDTO.getCoverUrl());

        List<User> userList = userDao.getItemUserList(itemId);

        List<Team> itemTeamList = teamDao.getItemTeamList(itemId);
        //-----------------------------------------------------
        List<UserDTO> userDTOS = ConversionUtils.convertList(userList, UserDTO.class);
        for (UserDTO userDTO :
                userDTOS) {
            userDTO.setAvatarUrl(ResourcePathConstant.VIRTUAL_USER_AVATAR_URL_BASE + userDTO.getAvatarUrl());
        }
        List<TeamDTO> teamDTOS = ConversionUtils.convertList(itemTeamList, TeamDTO.class);

        for (TeamDTO teamDTO :
                teamDTOS) {
            teamDTO.setTeamAvatar(ResourcePathConstant.VIRTUAL_TEAM_COVER_BASE + teamDTO.getTeamAvatar());
        }
        return new ItemBO(itemDTO, userDTOS, teamDTOS);
    }
}
