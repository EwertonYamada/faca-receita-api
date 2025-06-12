package com.faca_receita.helpers.pagination;

public class Pagination {
    private Integer page;
    private Integer pageSize;
    private Integer totalItems;
    private Integer totalPages;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }
}
