package com.example.Parcial.controller;

import com.example.Parcial.model.Persona;
import com.example.Parcial.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/persona")

public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService){this.personaService=personaService;}

    @GetMapping
    public List<Persona> getAll(){return personaService.getAll();}

    @GetMapping("/{id}")
    public Persona getPersonaByID(@PathVariable Integer id){return personaService.getPersonaByID(id);}

    @PostMapping("/")
    public void addPersona(@RequestBody Persona newPerson){
        personaService.addPersona(newPerson);
    }

    @DeleteMapping("/{id}")
    public void delPersonaByID(@PathVariable Integer id){personaService.delPersonaByID(id);}

    @PutMapping("/{id}/persona/{idjugador}")
    public void addJugadorToRepresentante(@PathVariable Integer id, @PathVariable Integer idJugador) {
        personaService.addJugadorToRepresentante(id,idJugador);
    }

}

