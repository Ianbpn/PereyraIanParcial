package com.example.Parcial.model;

public enum TypeStatusSocial {
    RICO("Rico"),
    POBRE("Pobre");

    private String descripcion;


    TypeStatusSocial(String descripcion) {this.descripcion=descripcion;}

    public static TypeStatusSocial find(final String value) {
        for (TypeStatusSocial S : values()) {
            if (S.toString().equalsIgnoreCase(value)) {
                return S;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypeCurrency: %s", value));
    }

    public String getDescripcion() {
        return descripcion;
    }
}
