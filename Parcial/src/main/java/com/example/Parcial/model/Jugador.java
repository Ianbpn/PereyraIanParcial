package com.example.Parcial.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Jugador extends Persona{

    private int peso;
    private int altura;
    private int goles;
    private int minutosJugados;
    private Currency currency;
    private Date fechaNacimiento;

    public Jugador(int id, String name, String lastName, int peso, int altura, int goles, int minutosJugados, Currency currency, Date fechaNacimiento) {
        super(id, name, lastName);
        this.peso = peso;
        this.altura = altura;
        this.goles = goles;
        this.minutosJugados = minutosJugados;
        this.currency = currency;
        this.fechaNacimiento = fechaNacimiento;
    }


    @Override
    public TypePersona typePersona(){return TypePersona.JUGADOR;}

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
