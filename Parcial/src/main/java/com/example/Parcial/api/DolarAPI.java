package com.example.Parcial.api;


import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DolarAPI {

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("compra")
    private Double compra;

    @SerializedName("venta")
    private Double venta;

    @SerializedName("agencia")
    private Integer agencia;


}
