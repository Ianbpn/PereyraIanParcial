package com.example.Parcial.service;


import com.example.Parcial.exception.*;
import com.example.Parcial.model.*;
import com.example.Parcial.model.Cumpleañitos;
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

    //Teniendo PersonaService en CumpleañitosService y CumpleañitosService en PersonaService me genera un bucle y no se como cambiar la logica del enunsiado para que no suceda
    //public CumpleañitosService cumpleañitosService;


    @Autowired
    public PersonaService(PersonaRepository personaRepositor /*,CumpleañitosService cumpleañitosService*/){
     this.personaRepository=personaRepositor;
     //this.cumpleañitosService=cumpleañitosService;
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

    public void addJugadorToAmigo(Integer id, Integer idAmigo){
        Persona represante = getPersonaByID(id);
        Persona amigo = getPersonaByID(idAmigo);

        if(!(represante instanceof Representante) || !(amigo instanceof Amigo)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (((Representante) represante).getAmigosList().contains(amigo)){
            throw new AlreadyExistsException(String.format("Player " + amigo + " "));
        }
        ((Representante) represante).getAmigosList().add((Amigo) amigo);
        personaRepository.save(represante);
    }

    /*public void addCumpleañitosToPersona(Integer id, Integer idCumpleañitos){
        Persona persona = getPersonaByID(id);
        Cumpleañitos cumpleañitos = cumpleañitosService.getCumpleañitosByID(idCumpleañitos);

        if(!(persona instanceof Persona) || !(cumpleañitos instanceof Cumpleañitos)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (((Persona) persona).getCumpleañitos().contains(cumpleañitos)){
            throw new AlreadyExistsException(String.format("Player " + cumpleañitos + " "));
        }
        ((Persona) persona).getCumpleañitos().add(cumpleañitos);
        personaRepository.save(persona);
    }*/
}


