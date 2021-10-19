package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

/**
 * 成就
 *
 * @author MysticalDream
 */
public class AchievementController {
    /**
     * 获取成就
     *
     * @return
     */
    @MyRequestPath(value = "/achievement")
    public String getAchievement(HttpInfoWrapper httpInfoWrapper) {
        
        return "获取项目成就";
    }
}
