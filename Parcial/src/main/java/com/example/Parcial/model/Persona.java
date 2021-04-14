package com.example.Parcial.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,property = "TypePersona",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Jugador.class, name="JUGADOR"),
        @JsonSubTypes.Type(value = Representante.class, name = "REPRESENTANTE")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Persona {
    @Id
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String lastName;

    public Persona(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
    @AccessType(AccessType.Type.PROPERTY)
    public abstract TypePersona typePersona();
}
