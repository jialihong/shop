package com.amity.common.web;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Amity on 2020/12/23 15:40
 */
public class PageResult<T> implements Serializable {

    private Long total;
    private Long pageCount;
    private Long pageSize;
    private Long pageNum;
    private List<T> content;

    public PageResult(List<T> content, long pageNum, long pageSize, long total) {
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.content = content;
        pageCount = (total + pageSize - 1) / pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public List<T> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
