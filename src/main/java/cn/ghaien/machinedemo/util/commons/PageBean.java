package cn.ghaien.machinedemo.util.commons;

import java.util.List;

/**
 * 分页返回对象中的数据
 *
 * @author guo.haien
 * @date 2018/10/8 23:53
 */
public class PageBean<T> {

    private List<T> data;

    private Long totalNum;

    public PageBean() {
    }

    public PageBean(List<T> data, Long totalNum) {
        this.data = data;
        this.totalNum = totalNum;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }
}
