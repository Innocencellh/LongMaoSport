package com.live.longmao.model;

import java.util.List;

/**
 * Created by HPDN on 2016/11/28.
 */
public class GiftListInfoBean {
    private int currentPage;
    private int itemCount;
    private int pageSize;
    private int pageTotal;

    private List<GiftInfo> list;

    public List<GiftInfo> getList() {
        return list;
    }

    public void setList(List<GiftInfo> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
}
