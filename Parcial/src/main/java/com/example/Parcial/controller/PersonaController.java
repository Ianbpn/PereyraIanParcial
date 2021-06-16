package com.example.Parcial.controller;

import com.example.Parcial.model.JugadorConversionDTO;
import com.example.Parcial.model.Persona;
import com.example.Parcial.model.PersonaConversionDTO;
import com.example.Parcial.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
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

    @PutMapping("/{id}/persona/{idamigo}")
    public void addAmigoToRepresentante(@PathVariable Integer id, @PathVariable Integer idAmigo) {
        personaService.addJugadorToRepresentante(id,idAmigo);
    }

    @GetMapping("/prodigy")
    public ResponseEntity<JugadorConversionDTO> prodigy(Pageable pageable) throws InterruptedException, ParseException, IOException {

        Page<JugadorConversionDTO> result = personaService.getJugadorConversion(pageable);
        return response(result);
    }

    public static ResponseEntity response(Page page){
        return ResponseEntity.status(page.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK)
                .header("X-total-Pages", String.valueOf(page.getTotalPages()))
                .header("X-total-Content", String.valueOf(page.getTotalElements()))
                .body(page.getContent());


    }

    /*@PutMapping("/{id}/cumpleañitos/{idcumpleañitos}")
    public void addCumpleañitosToPersona(@PathVariable Integer id, @PathVariable Integer idCumpleañitos) {
        personaService.addCumpleañitosToPersona(id,idCumpleañitos);
    }*/

}

