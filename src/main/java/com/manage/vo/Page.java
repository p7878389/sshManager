package com.manage.vo;

import java.util.List;

/**
 * 类名：Page <br/>
 * 功能说明： 分页对象<br/>
 * 修改历史： <br/>
 * 1.[2016/9/5  17:41]创建类 by pxh
 */
public class Page<T> {

    // 1.每页显示数量
    private String pageSize = "10";
    // 2.总记录数
    private int totalCount;
    // 3.总页数
    private int totalPage;
    // 4.当前页
    private String currentPage = "1";
    // 5.起始点
    private int beginIndex = 0;

    private List<T> results;

    public Page() {
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if (this.totalCount % Integer.parseInt( pageSize ) == 0) {
            this.totalPage = totalCount / Integer.parseInt( pageSize );
        } else {
            this.totalPage = totalCount / Integer.parseInt( pageSize ) + 1;
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public int getBeginIndex() {
        this.beginIndex = (Integer.parseInt( currentPage ) - 1) * Integer.parseInt( pageSize );
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

}
