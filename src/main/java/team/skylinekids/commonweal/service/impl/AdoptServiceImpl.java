package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.AdoptDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Adopt;
import team.skylinekids.commonweal.service.AdoptService;

import java.util.List;

/**
 * @author MysticalDream
 */
public class AdoptServiceImpl implements AdoptService {

    AdoptDao adoptDao = DaoFactory.getAdoptDao();

    @Override
    public int addAdoptInfo(Adopt adopt) throws Exception {
        return adoptDao.addAdoptInfo(adopt);
    }

    @Override
    public Adopt getAdoptById(Integer id) throws Exception {
        Adopt adopt = new Adopt();
        adopt.setAdoptId(id);
        return adoptDao.getAdopt(adopt);
    }

    @Override
    public Page<Adopt> getAdoptList(Page<Adopt> page, boolean option) throws Exception {
        return adoptDao.getAdoptList(page, option);
    }

    @Override
    public int updateAdoptUserId(Adopt adopt) throws Exception {
        return adoptDao.updateAdoptUserId(adopt);
    }

    @Override
    public List<Adopt> getUserAdopt(Integer userId, Page<Adopt> adoptPage) throws Exception {
        return adoptDao.getUserAdopt(userId, adoptPage);
    }
}
