package com.example.Parcial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class JugadorConversionDTO {
    private String fullname;
    private Integer age;
    private Float Height;
}
