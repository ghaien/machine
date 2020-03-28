package cn.ghaien.machinedemo.entity.input;

/**
 * Created by rt on 2018/10/8.
 */
public class PageQuery {

    private Integer pageNo = 0;

    private Integer pageSize = 10;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
