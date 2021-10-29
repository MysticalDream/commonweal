package dao;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.dao.AchievementDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.Achievement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AchievementDaoImplTest {

    AchievementDao achievementDao = DaoFactory.getAchievementDao();

    @Test
    void addAchievement() {
    }

    @Test
    void updateAchievement() {
    }

    @Test
    void getAchievementList() {
    }

    @Test
    void getAchievementByLimit() {
    }

    @Test
    void updateLoveNumber() {
    }

    @Test
    void getAchievementTopThree() throws Exception {
        List<Achievement> topThree = achievementDao.getAchievementTopThree();
        topThree.forEach(System.out::println);
    }
}