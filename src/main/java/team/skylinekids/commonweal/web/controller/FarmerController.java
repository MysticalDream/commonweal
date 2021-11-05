package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FarmerInfo;
import team.skylinekids.commonweal.pojo.po.Province;
import team.skylinekids.commonweal.pojo.query.FarmerInfoCondition;
import team.skylinekids.commonweal.service.FarmerInfoService;
import team.skylinekids.commonweal.service.ProvinceService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Part;

/**
 * 助农信息展示
 *
 * @author MysticalDream
 */
public class FarmerController {
    private final Logger logger = Logger.getLogger(FarmerController.class);
    FarmerInfoService farmerInfoService = ServiceFactory.getFarmerInfoService();
    ProvinceService provinceService = ServiceFactory.getProvinceService();

    /**
     * 获取助农信息
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/agriculture/list", type = {RequestMethod.GET})
    public String getFarmerInfoList(HttpInfoWrapper httpInfoWrapper) throws Exception {
        FarmerInfoCondition farmerInfoCondition = FillBeanUtils.fill(httpInfoWrapper.getParameterMap(), FarmerInfoCondition.class);
        Page<FarmerInfo> farmerInfoList = farmerInfoService.getFarmerInfoList(farmerInfoCondition);

        Province province = new Province();

        for (FarmerInfo farmerInfo : farmerInfoList.getList()) {
            province.setProvince(farmerInfo.getProvince());
            province.setCity(farmerInfo.getCity());
            province.setArea(farmerInfo.getArea());
            province.setTown(farmerInfo.getTown());
            farmerInfo.setLocation(provinceService.getCityNameByCondition(province));
        }
        return ResultUtils.getResult(ApiResultCode.SUCCESS, farmerInfoList);
    }

    /**
     * 添加助农信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/agriculture", type = {RequestMethod.POST})
    public String addFarmerInfo(HttpInfoWrapper httpInfoWrapper) throws Exception {
        FarmerInfo farmerInfo = GsonUtils.j2O(httpInfoWrapper.getJsonString(), FarmerInfo.class);
        String coverUrl = farmerInfo.getCoverUrl();
        //文件名
        String fileName = FileUtils.getFileName(coverUrl);
        //把封面从暂存区放到真正的目录中
        if (!FileUtils.cutFile(ResourcePathConstant.DISK_FARMER_TEMP_IMG_BASE + fileName, ResourcePathConstant.DISK_FARMER_IMG_BASE + fileName)) {
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
        farmerInfo.setCoverUrl(fileName);
        farmerInfoService.addFarmerInfo(farmerInfo);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 上传助农封面
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/agriculture/cover", type = {RequestMethod.POST})
    public String uploadFarmerCover(HttpInfoWrapper httpInfoWrapper) {
        Part coverPart = httpInfoWrapper.getPart("agriculture_cover");
        if (coverPart == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        try {
            String fileName = FileUtils.saveResourceByPart(coverPart, ResourcePathConstant.DISK_FARMER_TEMP_IMG_BASE);
            return ResultUtils.getResult(ApiResultCode.SUCCESS, ResourcePathConstant.VIRTUAL_FARMER_COVER_TEMP_BASE + fileName);
        } catch (Exception e) {
            logger.error("招募志愿者封面上传处理失败", e);
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
    }

}
