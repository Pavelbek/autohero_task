package com.autohero.ui.enums;

public enum CarsSortingTypes {
    NEUESTE_INSERATE_ZUERST("Neueste Inserate zuerst"),
    NIEDRIGSTER_PREIS("Niedrigster Preis"),
    HOCHSTER_PREIS("Höchster Preis"),
    NIEDRIGSTER_KILOMETER_STAND("Niedrigster Kilometer-Stand"),
    HOCHSTE_DISTANZ("Höchste Distanz"),
    MARKE_A_BIS_Z("Marke - A bis Z"),
    MARKE_Z_BIS_A("Marke - Z bis A");

    private final String sortingType;

    CarsSortingTypes(String sortingType) {
        this.sortingType = sortingType;
    }

    public String getSortingType(){
        return this.sortingType;
    }
}
