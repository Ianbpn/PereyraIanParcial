package com.example.Parcial.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Jugador extends Persona{

    private Integer peso;
    private Integer altura;
    private Integer goles;
    private Integer minutosJugados;
    private Date fechaNacimiento;

    //dudoso
    @ManyToOne(fetch = FetchType.EAGER)
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Representante representante;

    @Override
    public TypePersona typePersona(){return TypePersona.JUGADOR;}

}
