package com.example.Parcial.controller;

import com.example.Parcial.model.Cumpleañitos;
import com.example.Parcial.model.Persona;
import com.example.Parcial.service.CumpleañitosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/cumpleañitos")
public class CumpleañitosController {
    private final CumpleañitosService cumpleañitosService;

    @Autowired
    public CumpleañitosController(CumpleañitosService cumpleañitosService){this.cumpleañitosService=cumpleañitosService;}

    @GetMapping("/")
    public List <Cumpleañitos> getAll(){return cumpleañitosService.getAll();}

    @GetMapping("/{id}")
    public Cumpleañitos getCumpleañitosByID(@PathVariable Integer id){return cumpleañitosService.getCumpleañitosByID(id);}


    @PostMapping("/")
    public void addCumpleañitos(@RequestBody Cumpleañitos cumpleañitos){
        cumpleañitosService.addCumpleañitos(cumpleañitos);
    }

    @PutMapping("/{id}/persona/{idpersona}")
    public void addPersonaToCumpleañitos(@PathVariable Integer id, @PathVariable Integer idPersona) {
        cumpleañitosService.addPersonaToCumpleañitos(id,idPersona);
    }
}
