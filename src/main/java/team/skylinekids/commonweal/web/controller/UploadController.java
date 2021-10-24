package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * 上传图片控制器
 *
 * @author MysticalDream
 */
@WebServlet("/cover")
@MultipartConfig
public class UploadController extends HttpServlet {

    private final Logger logger = Logger.getLogger(UploadController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write(uploadHandle(new HttpInfoWrapper(resp, req, "")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(ResultUtils.getResult(ApiResultCode.REQUEST_METHOD_NOT_ALLOWED));
    }

    /**
     * 封面上传
     *
     * @param httpInfoWrapper
     * @return
     */
    public String uploadHandle(HttpInfoWrapper httpInfoWrapper) {
        String partName = httpInfoWrapper.getParameter("part_name");
        String tempPath, virtualPath;
        switch (partName) {
            case "team_cover":
                tempPath = ResourcePathConstant.DISK_TEAM_COVER_TEMP_BASE_URL;
                virtualPath = ResourcePathConstant.VIRTUAL_TEAM_COVER_TEMP_BASE;
                break;
            case "achievement_cover":
                tempPath = ResourcePathConstant.DISK_ACHIEVEMENT_TEMP_IMG_BASE;
                virtualPath = ResourcePathConstant.VIRTUAL_ACHIEVEMENT_TEMP_IMG_BASE;
                break;
            case "recruit_cover":
                tempPath = ResourcePathConstant.DISK_RECRUIT_COVER_TEMP_BASE_URL;
                virtualPath = ResourcePathConstant.VIRTUAL_RECRUIT_COVER_TEMP_BASE;
                break;
            case "item_cover":
                tempPath = ResourcePathConstant.DISK_ITEM_COVER_TEMP_BASE_URL;
                virtualPath = ResourcePathConstant.VIRTUAL_ITEM_COVER_TEMP_BASE;
                break;
            case "agriculture_cover":
                tempPath = ResourcePathConstant.DISK_FARMER_TEMP_IMG_BASE;
                virtualPath = ResourcePathConstant.VIRTUAL_FARMER_COVER_TEMP_BASE;
                break;
            case "adopt_cover":
                tempPath = ResourcePathConstant.DISK_ADOPT_TEMP_IMG_BASE;
                virtualPath = ResourcePathConstant.VIRTUAL_ADOPT_COVER_TEMP_BASE;
                break;
            default:
                return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Part part = httpInfoWrapper.getPart(partName);
        return handleCover(part, tempPath, virtualPath);
    }

    /**
     * 处理封面
     *
     * @param coverPart
     * @param diskTempPath
     * @param virtualTempPath
     * @return
     */
    private String handleCover(Part coverPart, String diskTempPath, String virtualTempPath) {
        if (coverPart == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        try {
            String fileName = FileUtils.saveResourceByPart(coverPart, diskTempPath);
            return ResultUtils.getResult(ApiResultCode.SUCCESS, virtualTempPath + fileName);
        } catch (Exception e) {
            logger.error("封面上传处理失败", e);
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
    }
}
