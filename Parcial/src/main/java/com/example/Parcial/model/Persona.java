package com.example.Parcial.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,property = "TypePersona",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Jugador.class, name="JUGADOR"),
        @JsonSubTypes.Type(value = Representante.class, name = "REPRESENTANTE"),
        @JsonSubTypes.Type(value = Amigo.class, name = "AMIGO")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String lastName;

    @ManyToMany(mappedBy = "Cumpleaños-Personas")
    private Set<Cumpleañitos> cumpleañitos;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract TypePersona typePersona();
}
