package com.devoir1.moetezbenyemna.devoir1.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.devoir1.moetezbenyemna.devoir1.entities.Song;
import com.devoir1.moetezbenyemna.devoir1.entities.Categories;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByNomSong(String nom);

    List<Song> findByNomSongContains(String nom);

    @Query("select s from Song s where s.nomSong like %:nom and s.prixSong > :prix")
    List<Song> findByNomPrix(@Param("nom") String nom, @Param("prix") Double prix);

    @Query("select s from Song s where s.categorie = ?1")
    List<Song> findByCategorie(Categories categorie);

    List<Song> findByCategorieIdCat(Long id);

    List<Song> findByOrderByNomSongAsc();

    @Query("select s from Song s order by s.nomSong ASC, s.prixSong DESC")
    List<Song> trierSongsNomsPrix();
}
