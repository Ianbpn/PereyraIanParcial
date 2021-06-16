package com.example.Parcial.controller;

import com.example.Parcial.AbstractController;
import com.example.Parcial.model.PersonaConversionDTO;
import com.example.Parcial.service.CumpleañitosService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(classes = CumpleañitosController.class)
public class CumpleañitosControllerTest extends AbstractController {

    @MockBean
    private CumpleañitosService cumpleañitosService;
    @MockBean
    private CumpleañitosController cumpleañitosController;

    @Test
    public void getInvitadosConversionTest(){
        try {
            when(cumpleañitosService.getPersonaConversion(any())).thenReturn(aPersonaConversionList());
            ResponseEntity<PersonaConversionDTO> responseEntity = cumpleañitosController.payCumpleaños(aPageable());

            assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
            assertEquals(aPersonaConversionList().getContent().get(0).getAmount(), responseEntity.getBody().getClass());
        } catch (InterruptedException | ParseException | IOException e) {
            fail();
        }

    }

    private static Page<PersonaConversionDTO> aPersonaConversionList(){
        PersonaConversionDTO b = new PersonaConversionDTO();
        b.setAmount(20f);
        return new PageImpl<>(List.of(b));
    }

    public static Pageable aPageable(){
        return PageRequest.of(0,10);
    }
}
