package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.AdoptDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Adopt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MysticalDream
 */
public class AdoptDaoImpl extends MyGenericBaseDao<Adopt> implements AdoptDao {
    @Override
    public int addAdoptInfo(Adopt adopt) throws Exception {
        return this.insert(adopt);
    }

    @Override
    public Page<Adopt> getAdoptList(Page<Adopt> page, boolean option) throws Exception {
        List<Adopt> list;
        String condition = option
                //已领养
                ? " WHERE IFNULL(adopt_user_id,'null')!='null' AND status=1 "
                //未领养
                : " WHERE status!=1 ";
        list = this.getListByPagination(condition, page);
        list.forEach(adopt -> adopt.setCoverUrl(ResourcePathConstant.VIRTUAL_ADOPT_COVER_BASE + adopt.getCoverUrl()));
        return page;
    }

    @Override
    public int updateAdoptUserId(Adopt adopt) throws Exception {
        return this.update(adopt);
    }

}
