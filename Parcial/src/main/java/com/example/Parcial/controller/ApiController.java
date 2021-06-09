package com.example.Parcial.controller;

import com.example.Parcial.api.ApiCallService;
import com.example.Parcial.api.DolarAPI;
import com.example.Parcial.api.EuroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")

public class ApiController {
    @Autowired
    private ApiCallService apiCallService;

    @GetMapping
    public EuroAPI getEuroApi() throws IOException, InterruptedException{
        return apiCallService.getEuroApi();
    }

    @GetMapping
    public DolarAPI getDolarApi() throws IOException, InterruptedException{
        return apiCallService.getDolarApi();
    }
}
