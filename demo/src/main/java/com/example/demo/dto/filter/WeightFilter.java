package com.example.demo.dto.filter;

public class WeightFilter extends BaseFilter {
    private Integer dtStart;
    private Integer dtEnd;

    public WeightFilter(Integer page, Integer size, ESortDirection sortDirection, Integer dtStart, Integer dtEnd) {
        super(page, size, sortDirection);
        this.dtStart=dtStart;
        this.dtEnd=dtEnd;
    }

    public Integer getDtStart() {
        return dtStart;
    }

    public void setDtStart(Integer dtStart) {
        this.dtStart = dtStart;
    }

    public Integer getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(Integer dtEnd) {
        this.dtEnd = dtEnd;
    }
}
