package com.faca_receita.helpers.generalEnums;

public enum MeasureUnit {
    MILLIGRAM("miligrama", "mg"),
    GRAM("grama", "g"),
    KILOGRAM("quilograma", "kg"),
    MILLILITER("mililitro", "ml"),
    LITER("litro", "l"),
    TEASPOON("colher de chá", "cchá"),
    TABLESPOON("colher de sopa", "cs"),
    CUP("xícara", "xic"),
    UNIT("unidade", "un"),
    DOZEN("dúzia", "dz"),
    HUNDRED("centena", "ct"),
    SERVING("porção", "porção"),
    SLICE("fatia", "fatia"),
    PACK("pacote", "pct"),
    KIT("kit", "kit"),
    PINCH("pitada", "pitada"),
    DROP("gota", "gota"),
    TABLET("tablete", "tbl");

    private final String name;
    private final String abbreviation;

    MeasureUnit(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}