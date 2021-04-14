package com.example.Parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@Entity

public class Representante extends Persona{
    private List<Jugador> jugadoresList;
    private int pesoDeLaBoveda;
    private int montoTooal;

    public Representante(int id, String name, String lastName, List<Jugador> jugadoresList, int pesoDeLaBoveda, int montoTooal) {
        super(id, name, lastName);
        this.jugadoresList = jugadoresList;
        this.pesoDeLaBoveda = pesoDeLaBoveda;
        this.montoTooal = montoTooal;
    }

    @Override
    public TypePersona typePersona() {return TypePersona.REPRESENTANTE;}

    public List<Jugador> getJugadoresList() {
        return jugadoresList;
    }

    public void setJugadoresList(List<Jugador> jugadoresList) {
        this.jugadoresList = jugadoresList;
    }

    public int getPesoDeLaBoveda() {
        return pesoDeLaBoveda;
    }

    public void setPesoDeLaBoveda(int pesoDeLaBoveda) {
        this.pesoDeLaBoveda = pesoDeLaBoveda;
    }

    public int getMontoTooal() {
        return montoTooal;
    }

    public void setMontoTooal(int montoTooal) {
        this.montoTooal = montoTooal;
    }
}
