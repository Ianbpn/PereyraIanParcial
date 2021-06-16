package com.example.Parcial.service;


import com.example.Parcial.api.ApiCallService;
import com.example.Parcial.exception.*;
import com.example.Parcial.model.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Parcial.repository.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PersonaService{

    public PersonaRepository personaRepository;
    private final ApiCallService apiCallService;
    //Teniendo PersonaService en CumpleañitosService y CumpleañitosService en PersonaService me genera un bucle y no se como cambiar la logica del enunsiado para que no suceda
    //public CumpleañitosService cumpleañitosService;


    @Autowired
    public PersonaService(PersonaRepository personaRepositor, ApiCallService apiCallService /*,CumpleañitosService cumpleañitosService*/){
     this.personaRepository=personaRepositor;
     this.apiCallService=apiCallService;
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

    public Page<JugadorConversionDTO> getJugadorConversion(Pageable pageable) throws InterruptedException, IOException {

        //me tira un exception tratando de llamar al apiCallService y no puedo solucionarlo
        JsonObject jugadorApi = apiCallService.getJugadorApi();

        if(jugadorApi == null){
            throw new IOException();
        }
        List<JugadorConversionDTO> result = new ArrayList<>();

        //no puedo verificar si esto esta bien
        for (JsonElement jo : jugadorApi.getAsJsonArray())
        {
            if (jo["height"] >= "180" && jo["age"]<=20) {
                JugadorConversionDTO j = new JugadorConversionDTO();
                j.setFullname(jo["name"] +" "+ jo["lastname"]);
                j.setAge(jo["age"]);
                j.setHeight(jo["height"]);
                result.add(j);
            }
        }

        return new PageImpl<JugadorConversionDTO>(result,pageable,result.size());
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


