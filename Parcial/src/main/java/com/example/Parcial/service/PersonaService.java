package com.example.Parcial.service;


import com.example.Parcial.model.Currency;
import com.example.Parcial.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Parcial.repository.*;

import java.util.List;

@Service
public class PersonaService {

    public PersonaRepository personaRepository;
    public CurrencyRepository currencyRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepositor, CurrencyRepository currencyRepositor){
     personaRepository=personaRepositor;
     currencyRepository=currencyRepositor;
    }
    public List<Persona> getAll(){return  personaRepository.findAll();}

    public void addPersona  (Persona persona){personaRepository.save(persona);}

}


