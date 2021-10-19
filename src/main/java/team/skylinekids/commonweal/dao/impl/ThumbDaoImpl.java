package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.ThumbDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.Thumb;

/**
 * 点赞实现类
 *
 * @author MysticalDream
 */
public class ThumbDaoImpl extends MyGenericBaseDao<Thumb> implements ThumbDao {

    @Override
    public boolean clickLike(Thumb thumb) throws Exception {
        Thumb thumb1 = this.selectOne(thumb);
        if (thumb1 == null) {
            this.insert(thumb);
            return true;
        }
        if (thumb1.getStatus()) {
            thumb1.setStatus(false);
            this.update(thumb1);
            return false;
        } else {
            thumb1.setStatus(true);
            this.update(thumb1);
            return true;
        }
    }
}
