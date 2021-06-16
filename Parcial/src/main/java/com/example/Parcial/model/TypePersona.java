package com.example.Parcial.model;



public enum TypePersona {
    JUGADOR("Jugador"),
    REPRESENTANTE("Representante"),
    AMIGO("Amigo");

    private String description;


    TypePersona(String description) {this.description =description;}

    static TypePersona find(final String value) {
        for (TypePersona P : values()) {
            if (P.toString().equalsIgnoreCase(value)) {
                return P;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypePersona: %s", value));
    }

    String getDescription() {
        return description;
    }

}
