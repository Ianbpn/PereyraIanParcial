package com.example.Parcial.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Jugador extends Persona{

    private Integer peso;
    private Integer altura;
    private Integer goles;
    private Integer minutosJugados;
    private Date fechaNacimiento;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Representante representante;


    @Override
    public TypePersona typePersona(){return TypePersona.JUGADOR;}

}
