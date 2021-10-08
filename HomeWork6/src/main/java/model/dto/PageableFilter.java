package model.dto;

public class PageableFilter {
    private int page;
    private int size;
    private ESortDirection sortDirection;

    public PageableFilter(int page, int size, ESortDirection sortDirection) {
        this.page = page;
        this.size = size;
        this.sortDirection = sortDirection;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ESortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(ESortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }
}
