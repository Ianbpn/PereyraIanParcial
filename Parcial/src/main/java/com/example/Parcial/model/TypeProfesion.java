package com.example.Parcial.model;

public enum TypeProfesion {
    CARNICERO("Carnicero"),
    PROGRAMADOR("Programador"),
    MEDICO("Medico");

    private String descripcion;


    TypeProfesion(String descripcion) {this.descripcion=descripcion;}

    public static TypeProfesion find(final String value) {
        for (TypeProfesion P : values()) {
            if (P.toString().equalsIgnoreCase(value)) {
                return P;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypeProfesion: %s", value));
    }

    public String getDescripcion() {
        return descripcion;
    }
}
