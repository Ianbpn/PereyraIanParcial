package com.example.Parcial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Amigo extends Persona{

    private TypeProfesion profesion;
    private TypeStatusSocial statusSocial;


    @Override
    public TypePersona typePersona() {
        return TypePersona.AMIGO;
    }
}
