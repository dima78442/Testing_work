
package com.dima.testing_work.data.Network.model.model.search; ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("effective_limit")
    @Expose
    private Integer effectiveLimit;
    @SerializedName("effective_offset")
    @Expose
    private Integer effectiveOffset;
    @SerializedName("next_offset")
    @Expose
    private Integer nextOffset;
    @SerializedName("effective_page")
    @Expose
    private Integer effectivePage;
    @SerializedName("next_page")
    @Expose
    private Integer nextPage;

    public Integer getEffectiveLimit() {
        return effectiveLimit;
    }

    public void setEffectiveLimit(Integer effectiveLimit) {
        this.effectiveLimit = effectiveLimit;
    }

    public Integer getEffectiveOffset() {
        return effectiveOffset;
    }

    public void setEffectiveOffset(Integer effectiveOffset) {
        this.effectiveOffset = effectiveOffset;
    }

    public Integer getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
    }

    public Integer getEffectivePage() {
        return effectivePage;
    }

    public void setEffectivePage(Integer effectivePage) {
        this.effectivePage = effectivePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

}
