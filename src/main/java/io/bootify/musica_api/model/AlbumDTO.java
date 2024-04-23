package io.bootify.musica_api.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AlbumDTO {

    private Integer id;

    @Size(max = 255)
    private String titulo;

    @Size(max = 255)
    private String fecha_lanzamiento;

    private Integer artista_id;

}
