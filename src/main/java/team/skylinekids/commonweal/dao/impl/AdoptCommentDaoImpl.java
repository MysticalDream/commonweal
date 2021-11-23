package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.AdoptCommentDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.AdoptComment;

import java.util.List;

/**
 * @author MysticalDream
 */
public class AdoptCommentDaoImpl extends MyGenericBaseDao<AdoptComment> implements AdoptCommentDao {
    @Override
    public int addAdoptComment(AdoptComment adoptComment) throws Exception {
        return this.insert(adoptComment);
    }

    @Override
    public List<AdoptComment> getAdoptCommentListByAdoptId(Integer adoptId, Page<AdoptComment> page) throws Exception {
        List<AdoptComment> listByPagination = this.getListByPagination(" WHERE adopt_id=" + adoptId + "ORDER BY is_top DESC,gmt_create ", page);
        return listByPagination;
    }
}
