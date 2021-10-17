package team.skylinekids.commonweal.pojo.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页模型
 *
 * @param <T> 分页的对象类型
 * @author MysticalDream
 */
public class Page<T> implements Serializable {

    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 第几页
     */
    private int pageNum = 1;
    /**
     * 总记录数
     */
    private int total;
    /**
     * 当前页的数量<=pageSize
     */
    private int size;
    /**
     * 总页数
     */
    private int pages;

    /**
     * 结果集
     */
    private List<T> list;


    public Page(int pageSize, int pageNum, int total, int size, List<T> list, int pages) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.total = total;
        this.size = size;
        this.list = list;
        this.pages = pages;
    }

    public Page() {

    }

    /**
     * 获取开始记录
     *
     * @return
     */
    public int getStartRow() {
        return (pageNum - 1) * pageSize;
    }

    /**
     * 获取最后的记录
     *
     * @return
     */
    public int getEndRow() {
        return pageNum * pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum > 0) {
            this.pageNum = pageNum;
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * 自动计算总页数,需要总记录数和每页显示数量
     */
    public void setPagesAuto() {
        if (pageSize == 0) {
            return;
        }
        pages = total / pageSize;
        if (total % pageSize != 0) {
            pages++;
        }
    }
}
