package com.example.Parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@ToString
public class Representante extends Persona{
    @OneToMany
    @JoinColumn(name = "persona_id")
    private List<Jugador> jugadoresList;
    private Integer pesoDeLaBoveda;

    private Integer montoTotal;

    @ManyToOne(targetEntity = Amigo.class,fetch = FetchType.EAGER)
    private List<Amigo> amigosList;

    @Override
    public TypePersona typePersona() {return TypePersona.REPRESENTANTE;}

    public List<Jugador> getJugadoresList() {
        return jugadoresList;
    }
    public List<Amigo> getAmigosList() {
        return amigosList;
    }

    public Integer getPesoDeLaBoveda(){
        return jugadoresList.stream().map(Jugador::getCurrency).map(Currency::getMonto).reduce(0,Integer::sum);
    }
    public void setPesoDeLaBoveda(Integer pesoDeLaBoveda) {
        this.pesoDeLaBoveda = getPesoDeLaBoveda();
    }

}
