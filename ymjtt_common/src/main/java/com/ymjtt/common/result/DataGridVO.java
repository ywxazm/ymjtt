package com.ymjtt.common.result;

import java.io.Serializable;
import java.util.List;

/**
 * EASYUI需要的返回数据类型
 * @auther ywx
 * @date 2019/1/9 15:24
 **/
public class DataGridVO<T> implements Serializable {

    private Long total;
    private List<T> rows;

    public DataGridVO(List<T> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "DataGridVO{" + "total=" + total + ", rows=" + rows + '}';
    }
}
