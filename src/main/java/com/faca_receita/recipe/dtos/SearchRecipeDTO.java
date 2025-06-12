package com.faca_receita.recipe.dtos;

import com.faca_receita.helpers.pagination.Pagination;
import lombok.Getter;

import java.util.Date;

@Getter
public class SearchRecipeDTO extends Pagination {
    private String recipeName;
    private Date recipeDateStart;
    private Date recipeDateEnd;

    public String getRecipeName() {
        return recipeName;
    }

    public Date getRecipeDateStart() {
        return recipeDateStart;
    }

    public Date getRecipeDateEnd() {
        return recipeDateEnd;
    }
}
