package team.skylinekids.commonweal.pojo.po;

import java.io.Serializable;

/**
 * 省市信息
 *
 * @author MysticalDream
 */
public class Province implements Serializable {
    /**
     * 自增id
     */
    private Long id;
    /**
     * 行政区划代码
     */
    private Long code;
    /**
     * 名字
     */
    private String name;
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
     * 乡镇
     */
    private String town;

    public Province(Long id, Long code, String name, String province, String city, String area, String town) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.province = province;
        this.city = city;
        this.area = area;
        this.town = town;
    }

    public Province() {
    }

    public Province(String province, String city, String area) {
        this.province = province;
        this.city = city;
        this.area = area;
    }

    public Province(String province) {
        this.province = province;
        this.city = "0";
        this.area = "0";
        this.town = "0";
    }

    public Province(String province, String city) {
        this.province = province;
        this.city = city;
        this.area = "0";
        this.town = "0";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
