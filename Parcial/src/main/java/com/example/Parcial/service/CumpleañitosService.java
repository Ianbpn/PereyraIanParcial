package com.example.Parcial.service;

import com.example.Parcial.exception.AlreadyExistsException;
import com.example.Parcial.model.Cumpleañitos;
import com.example.Parcial.model.Persona;
import com.example.Parcial.repository.CumpleañitosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class CumpleañitosService {
    private final CumpleañitosRepository cumpleañitosRepository;
    private final PersonaService personaService;

    @Autowired
    public CumpleañitosService(CumpleañitosRepository cumpleañitosRepository,PersonaService personaService){this.cumpleañitosRepository=cumpleañitosRepository; this.personaService=personaService;}
    public void addCumpleañitos  (Cumpleañitos cumpleañitos){cumpleañitosRepository.save(cumpleañitos);}

    public Cumpleañitos getCumpleañitosByID(Integer id){
        return cumpleañitosRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Cumpleañitos> getAll(){return  cumpleañitosRepository.findAll();}

    public void addPersonaToCumpleañitos(Integer id, Integer idPersona){
        Cumpleañitos cumpleañitos = getCumpleañitosByID(id);
        Persona persona = personaService.getPersonaByID(idPersona);


        if(!(persona instanceof Persona) || !(cumpleañitos instanceof Cumpleañitos)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (( cumpleañitos).getInvitados().contains(cumpleañitos)){
            throw new AlreadyExistsException(String.format("Player " + persona + " "));
        }
        ( cumpleañitos).getInvitados().add(persona);
        cumpleañitosRepository.save(cumpleañitos);
    }
}
