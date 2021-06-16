package com.example.Parcial.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j

public class ApiCallService {

    @CircuitBreaker(name="euro", fallbackMethod = "fallback")
    public Float getEuroApi() throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .header("accept", "application/json")
                .method("GET",HttpRequest.BodyPublishers.noBody())
                .build();

            HttpResponse<String> response = null;
            try{
                response=HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        String valor = JsonParser.parseString(response.body()).getAsJsonArray().get(0).getAsJsonObject().get("dolar").getAsJsonObject().get("compra").getAsString();

        return Float.parseFloat(valor.replace(",", "."));
    }


    @CircuitBreaker(name="dolar", fallbackMethod = "fallback")
    public  Float getDolarApi() throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .header("accept","application/json")
                .method("GET",HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try{
            response=HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String valor = JsonParser.parseString(response.body()).getAsJsonArray().get(0).getAsJsonObject().get("casa").getAsJsonObject().get("compra").getAsString();

        return Float.parseFloat(valor.replace(",", "."));
    }
    public Float fallback(final Throwable t){
        log.info("Fallback cause,{}",t.toString());
        return -1F;
    }
}
