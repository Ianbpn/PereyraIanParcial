package com.example.Parcial.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity


public class Currency {
    @Id
    private int ID;
    @NotNull(message = "Aclace el tipo de moneda")
    private String Currency;
    @NotNull
    private int Monto;

}
