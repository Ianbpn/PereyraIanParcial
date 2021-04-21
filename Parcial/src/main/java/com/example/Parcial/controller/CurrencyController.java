package com.example.Parcial.controller;


import com.example.Parcial.model.Currency;
import com.example.Parcial.model.Persona;
import com.example.Parcial.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService){this.currencyService=currencyService;}

    @GetMapping ("/")
    public List<Currency>getAll(){return currencyService.getAll();}

    @PostMapping("/")
    public void addCurrency(@RequestBody Currency currency) {
        currencyService.addCurrency(currency);
    }

    @GetMapping("/{id}")
    public Currency getCurrencyByID(@PathVariable Integer id){return currencyService.getCurrencyByID(id);}

    @DeleteMapping("/{id}")
    public void delCurrencyByID(@PathVariable Integer id){currencyService.delCurrencyByID(id);}


}
