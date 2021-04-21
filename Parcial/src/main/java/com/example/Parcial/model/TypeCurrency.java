package com.example.Parcial.model;

public enum TypeCurrency {

    EURO("Euro"),
    DOLAR("Dolar"),
    PESO("Peso");

    private String descripcion;


    TypeCurrency(String descripcion) {this.descripcion=descripcion;}

    public static TypeCurrency find(final String value) {
        for (TypeCurrency C : values()) {
            if (C.toString().equalsIgnoreCase(value)) {
                return C;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypeCurrency: %s", value));
    }

    public String getDescripcion() {
        return descripcion;
    }
}
