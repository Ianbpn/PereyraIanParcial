package com.example.Parcial.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Parcial.repository.*;
import com.example.Parcial.model.Currency;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@Service
public class CurrencyService {

    public CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository){
        this.currencyRepository =currencyRepository;
    }
    public List<Currency> getAll(){return  currencyRepository.findAll();}

    public void addCurrency (Currency currency){
        currencyRepository.save(currency);
    }

    public Currency getCurrencyByID(Integer id){
       return currencyRepository.findById(id)
               .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
    public void delCurrencyByID(Integer id){
        currencyRepository.deleteById(id);
    }
}
