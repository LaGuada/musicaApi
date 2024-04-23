package io.bootify.musica_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Album {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String titulo;

    @Column
    private String fecha_lanzamiento;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista_id", referencedColumnName = "id")
    private Artista artista;
}
