package com.example.Parcial.controller;

import com.example.Parcial.model.Cumpleañitos;
import com.example.Parcial.model.Persona;
import com.example.Parcial.model.PersonaConversionDTO;
import com.example.Parcial.service.CumpleañitosService;
import com.example.Parcial.util.EntityUrlBuilder;
import com.example.Parcial.util.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
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
    public ResponseEntity<PostResponse> addCumpleañitos(@RequestBody Cumpleañitos cumpleañitos){
        cumpleañitosService.addCumpleañitos(cumpleañitos);
        PostResponse postResponse = new PostResponse(EntityUrlBuilder.buildURL("/cumpleañitos",cumpleañitos.getId().toString()), HttpStatus.CREATED.getReasonPhrase());
        return ResponseEntity.created(URI.create(postResponse.getUrl())).body(postResponse);
    }

    @GetMapping("/pay")
    public ResponseEntity<PersonaConversionDTO> payCumpleaños(Pageable pageable) throws InterruptedException, ParseException, IOException{
        Page<PersonaConversionDTO> result = cumpleañitosService.getPersonaConversion(pageable);
        return response(result);
    }

    public static ResponseEntity response(Page page){
        return ResponseEntity.status(page.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK)
                .header("X-total-Pages", String.valueOf(page.getTotalPages()))
                .header("X-total-Content", String.valueOf(page.getTotalElements()))
                .body(page.getContent());


    }

    /*@PutMapping("/{id}/persona/{idpersona}")
    public void addPersonaToCumpleañitos(@PathVariable Integer id, @PathVariable Integer idPersona) {
        cumpleañitosService.addPersonaToCumpleañitos(id,idPersona);
    }*/


}
