package com.example.Parcial.service;

import com.example.Parcial.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Parcial.repository.*;
import com.example.Parcial.model.Currency;

import java.util.List;


@Service
public class CurrencyService {

    public CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepositor){
        currencyRepository =currencyRepositor;
    }
    public List<Currency> getAll(){return  currencyRepository.findAll();}

    public void addCurrency (Currency currency){
        currencyRepository.save(currency);
    }
}
