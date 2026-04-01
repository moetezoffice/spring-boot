package com.devoir1.moetezbenyemna.devoir1.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.devoir1.moetezbenyemna.devoir1.dto.SongDTO;
import com.devoir1.moetezbenyemna.devoir1.entities.Categories;
import com.devoir1.moetezbenyemna.devoir1.entities.Song;

public interface SongService {
    Song saveSong(Song s);
    Song updateSong(Song s);
    void deleteSong(Song s);
    void deleteSongById(Long id);
    Song getSong(Long id);
    List<Song> getAllSongs();
    Page<Song> getAllSongsParPage(int page, int size);

    // Search methods
    List<Song> findByNomSongContains(String nom);
    List<Song> findByCategorieIdCat(Long id);
    List<Song> findByOrderByNomSongAsc();
    List<Song> trierSongsNomsPrix();

    // Categories
    List<Categories> getAllCategories();

    // DTO pattern
    SongDTO convertEntityToDto(Song song);
    Song convertDtoToEntity(SongDTO dto);
}
