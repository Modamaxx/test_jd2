package com.example.demo.dto.filter;

public class WeightFilter extends BaseFilter {
    private Long dtStart;
    private Long dtEnd;

    public WeightFilter(Integer page, Integer size, ESortDirection sortDirection, Long dtStart, Long dtEnd) {
        super(page, size, sortDirection);
        this.dtStart=dtStart;
        this.dtEnd=dtEnd;
    }

    public Long getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(Long dtEnd) {
        this.dtEnd = dtEnd;
    }

    public Long getDtStart() {
        return dtStart;
    }

    public void setDtStart(Long dtStart) {
        this.dtStart = dtStart;
    }
}
