package com.example.Parcial.service;

import com.example.Parcial.api.ApiCallService;
import com.example.Parcial.model.*;
import com.example.Parcial.repository.CumpleañitosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CumpleañitosService {
    private final CumpleañitosRepository cumpleañitosRepository;
    private final ApiCallService apiCallService;
    //Teniendo PersonaService en CumpleañitosService y CumpleañitosService en PersonaService me genera un bucle y no se como cambiar la logica del enunsiado para que no suceda

    //private final PersonaService personaService;

    @Autowired
    public CumpleañitosService(CumpleañitosRepository cumpleañitosRepository,/* PersonaService personaService,*/ ApiCallService apiCallService){
        this.cumpleañitosRepository=cumpleañitosRepository; /*this.personaService=personaService;*/
        this.apiCallService = apiCallService;
    }
    public void addCumpleañitos  (Cumpleañitos cumpleañitos){cumpleañitosRepository.save(cumpleañitos);}

    public Cumpleañitos getCumpleañitosByID(Integer id){
        return cumpleañitosRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Cumpleañitos> getAll(){return  cumpleañitosRepository.findAll();}

    public Page<PersonaConversionDTO> getPersonaConversion(Pageable pageable) throws InterruptedException, ParseException, IOException{
        Page<Cumpleañitos> cumpleañitos=cumpleañitosRepository.findAll(pageable);

        Float euroValue = apiCallService.getEuroApi();
        Float dolarValue = apiCallService.getDolarApi();

        if(euroValue == -1 || dolarValue == -1){
            throw new IOException();
        }

        List<PersonaConversionDTO> result = new ArrayList<>();

        for(Cumpleañitos cumpleañito : cumpleañitos.getContent()){
            for (Persona persona : cumpleañito.getInvitados()){
                if (persona instanceof Jugador){
                    PersonaConversionDTO p = new PersonaConversionDTO();
                    p.setName(persona.getName() +" "+ persona.getLastName());
                    Currency currency = ((Jugador) persona).getCurrency();
                    if (currency.getTypeCurrency() == TypeCurrency.EURO ){
                        p.setAmount(25000 / euroValue);
                    }else{
                        p.setAmount(25000 / dolarValue);
                    }
                    p.setCurrency(((Jugador) persona).getCurrency().getTypeCurrency());
                    result.add(p);
                }
            }
        }
        return new PageImpl<PersonaConversionDTO>(result,pageable,result.size());
    }


    /*public void addPersonaToCumpleañitos(Integer id, Integer idPersona){
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
    }*/
}
