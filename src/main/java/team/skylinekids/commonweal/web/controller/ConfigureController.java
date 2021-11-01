package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.LevelCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.utils.CategoryUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.io.IOException;

/**
 * 配置接口
 *
 * @author MysticalDream
 */
public class ConfigureController {

    private final Logger logger = Logger.getLogger(ConfigureController.class);

    /**
     * 加载分类配置文件
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel(LevelCode.SPECIAL_LOGIN_LEVEL)
    @MyRequestPath(value = "/admin/config/classification", type = {RequestMethod.PUT})
    public String loadClassificationProperties(HttpInfoWrapper httpInfoWrapper) {
        try {
            CategoryUtils.loadProperties();
        } catch (IOException e) {
            logger.error("加载分类配置失败", e);
            return ResultUtils.getResult(ApiResultCode.SERVER_RUNNING_EXCEPTION);
        }
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }
}
