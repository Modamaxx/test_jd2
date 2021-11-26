package com.example.demo.dto.filter;

public class BaseFilter {

    private Integer page;
    private Integer size;
    private ESortDirection sortDirection;

    public BaseFilter(Integer page, Integer size, ESortDirection sortDirection) {
        this.page = page;
        this.size = size;
        this.sortDirection = sortDirection;
    }
    public BaseFilter(){

    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ESortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(ESortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }
}
