package com.devoir1.moetezbenyemna.devoir1.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.devoir1.moetezbenyemna.devoir1.dto.SongDTO;
import com.devoir1.moetezbenyemna.devoir1.entities.Categories;
import com.devoir1.moetezbenyemna.devoir1.entities.Song;
import com.devoir1.moetezbenyemna.devoir1.repo.CategoriesRepository;
import com.devoir1.moetezbenyemna.devoir1.repo.SongRepository;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongRepository songRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Song saveSong(Song s) {
        return songRepository.save(s);
    }

    @Override
    public Song updateSong(Song s) {
        return songRepository.save(s);
    }

    @Override
    public void deleteSong(Song s) {
        songRepository.delete(s);
    }

    @Override
    public void deleteSongById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Song getSong(Long id) {
        return songRepository.findById(id).get();
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Page<Song> getAllSongsParPage(int page, int size) {
        return songRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Song> findByNomSongContains(String nom) {
        return songRepository.findByNomSongContains(nom);
    }

    @Override
    public List<Song> findByCategorieIdCat(Long id) {
        return songRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Song> findByOrderByNomSongAsc() {
        return songRepository.findByOrderByNomSongAsc();
    }

    @Override
    public List<Song> trierSongsNomsPrix() {
        return songRepository.trierSongsNomsPrix();
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public SongDTO convertEntityToDto(Song song) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        SongDTO dto = modelMapper.map(song, SongDTO.class);
        // Manually map nomCat since it's from a nested object
        if (song.getCategorie() != null) {
            dto.setNomCat(song.getCategorie().getNomCat());
        }
        return dto;
    }

    @Override
    public Song convertDtoToEntity(SongDTO dto) {
        return modelMapper.map(dto, Song.class);
    }
}
