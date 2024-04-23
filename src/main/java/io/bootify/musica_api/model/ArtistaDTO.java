package io.bootify.musica_api.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ArtistaDTO {

    private Integer id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String genero;

}
