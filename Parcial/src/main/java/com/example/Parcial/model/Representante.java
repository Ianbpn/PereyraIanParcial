package com.example.Parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity

public class Representante extends Persona{
    @OneToMany(mappedBy = "persona")
    @JoinColumn
    private List<Jugador> jugadoresList;
    private Integer pesoDeLaBoveda;
    private Integer montoTotal;

    @Override
    public TypePersona typePersona() {return TypePersona.REPRESENTANTE;}

    public List<Jugador> getJugadoresList() {
        return jugadoresList;
    }

}
