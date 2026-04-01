package com.devoir1.moetezbenyemna.devoir1.entities;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "categorie")
@EqualsAndHashCode(exclude = "categorie")
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSong;

    @NotNull
    @Size(min = 2, max = 50, message = "Le nom doit être entre 2 et 50 caractères")
    private String nomSong;

    @Min(value = 0, message = "Le prix doit être positif")
    @Max(value = 10000, message = "Le prix ne peut pas dépasser 10 000")
    private double prixSong;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "La date doit être dans le passé ou aujourd'hui")
    private Date dateCreation;

    @ManyToOne
    private Categories categorie;

    public Song(String nomSong, double prixSong, Date dateCreation) {
        this.nomSong = nomSong;
        this.prixSong = prixSong;
        this.dateCreation = dateCreation;
    }
}
