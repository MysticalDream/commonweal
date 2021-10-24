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
    public Page<Adopt> getAdoptList(Page<Adopt> page) throws Exception {
        String sql = " LIMIT " + page.getStartRow() + "," + page.getPageSize();
        Integer count = this.selectAllCount();
        List<Adopt> adopts = this.selectListByConditionString(sql, new ArrayList<>());
        adopts.forEach(adopt -> adopt.setCoverUrl(ResourcePathConstant.VIRTUAL_ADOPT_COVER_BASE + adopt.getCoverUrl()));
        page.setList(adopts);
        page.setSize(adopts.size());
        page.setTotal(count);
        page.setPagesAuto();
        return page;
    }
}
