package com.example.Parcial.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@NoArgsConstructor
@JsonTypeInfo(property = "typePersona",visible = true, use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Jugador.class, name="JUGADOR"),
        @JsonSubTypes.Type(value = Representante.class, name = "REPRESENTANTE"),
        @JsonSubTypes.Type(value = Amigo.class, name = "AMIGO")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ToString
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min=0, max=30)
    private String name;

    @NotNull
    @Size(min=0, max=30)
    private String lastName;

    @ManyToMany
    private Set<Cumpleañitos> cumpleañitos;

    @NotNull
    @AccessType(AccessType.Type.PROPERTY)
    public abstract TypePersona typePersona();
}
