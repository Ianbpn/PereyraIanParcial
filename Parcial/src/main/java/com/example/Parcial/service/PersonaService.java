package com.example.Parcial.service;


import com.example.Parcial.exception.AlreadyExistsException;
import com.example.Parcial.model.Jugador;
import com.example.Parcial.model.Persona;
import com.example.Parcial.model.Representante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Parcial.repository.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class PersonaService{

    public PersonaRepository personaRepository;
   // public CurrencyRepository currencyRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepositor, CurrencyRepository currencyRepositor){
     personaRepository=personaRepositor;
   //  currencyRepository=currencyRepositor;
    }
    public List<Persona> getAll(){return  personaRepository.findAll();}

    public Persona getPersonaByID(Integer id){
        return personaRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void addPersona  (Persona persona){personaRepository.save(persona);}

    public void delPersonaByID(Integer id){
        personaRepository.deleteById(id);
    }

    public void addJugadorToRepresentante(Integer id, Integer idJugador){
        Persona represante = getPersonaByID(id);
        Persona jugador = getPersonaByID(idJugador);

        if(!(represante instanceof Representante) || !(jugador instanceof Jugador)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (((Representante) represante).getJugadoresList().contains(jugador)){
            throw new AlreadyExistsException(String.format("Player " + idJugador + " "));
        }
        ((Representante) represante).getJugadoresList().add((Jugador) jugador);
        personaRepository.save(represante);
    }
}


