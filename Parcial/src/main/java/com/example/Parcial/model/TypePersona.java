package com.example.Parcial.model;



public enum TypePersona {
    JUGADOR("Jugador"),
    REPRESENTANTE("Representante");

    private String descripcion;


    TypePersona(String descripcion) {this.descripcion=descripcion;}

    public static TypePersona find(final String value) {
        for (TypePersona P : values()) {
            if (P.toString().equalsIgnoreCase(value)) {
                return P;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypePersona: %s", value));
    }

    public String getDescripcion() {
        return descripcion;
    }

}
