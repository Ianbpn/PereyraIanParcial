package com.example.Parcial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PersonaConversionDTO {
    private String name;
    private TypeCurrency currency;
    private Float amount;
}
