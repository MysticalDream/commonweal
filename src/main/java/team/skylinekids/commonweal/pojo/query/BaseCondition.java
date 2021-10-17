package team.skylinekids.commonweal.pojo.query;

/**
 * 条件查询抽象类
 *
 * @author MysticalDream
 */
public abstract class BaseCondition {
    /**
     * 省份/直辖市
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区县
     */
    private String area;
    /**
     * 每页记录数
     */
    private Integer pageSize = 9;
    /**
     * 第几页
     */
    private Integer pageNum = 1;

    public BaseCondition(String province, String city, String area, Integer pageSize, Integer pageNum) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public BaseCondition() {
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

}
