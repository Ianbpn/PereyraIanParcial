package com.example.Parcial.model;

public enum TypeCurrency {

    EURO("Euro", 111f),
    DOLAR("Dolar", 92.67f);

    private String descripcion;
    private float pesoValue;


    TypeCurrency(String descripcion, float pesoValue) {this.descripcion=descripcion; this .pesoValue=pesoValue;}

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
