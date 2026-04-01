package com.devoir1.moetezbenyemna.devoir1.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongDTO {
    private Long idSong;
    private String nomSong;
    private double prixSong;
    private String nomCat; // flattened from categorie.nomCat
}
