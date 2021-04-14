package com.example.Parcial.controller;

import com.example.Parcial.model.Persona;
import com.example.Parcial.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")

public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAll(){return personaService.getAll();}



    @PostMapping
    public void addPersona(@RequestBody Persona persona){personaService.addPersona(persona);}
    /*@GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable String id){return personaService.getById(id);
    }
*/
}

