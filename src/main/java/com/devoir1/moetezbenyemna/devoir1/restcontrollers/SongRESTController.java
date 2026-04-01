package com.devoir1.moetezbenyemna.devoir1.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.devoir1.moetezbenyemna.devoir1.dto.SongDTO;
import com.devoir1.moetezbenyemna.devoir1.entities.Song;
import com.devoir1.moetezbenyemna.devoir1.service.SongService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SongRESTController {

    @Autowired
    SongService songService;

    // GET all songs (as DTO)
    @RequestMapping(method = RequestMethod.GET)
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs()
                .stream()
                .map(songService::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // GET by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SongDTO getSongById(@PathVariable("id") Long id) {
        return songService.convertEntityToDto(songService.getSong(id));
    }

    // POST - create
    @RequestMapping(method = RequestMethod.POST)
    public SongDTO createSong(@RequestBody Song song) {
        return songService.convertEntityToDto(songService.saveSong(song));
    }

    // PUT - update
    @RequestMapping(method = RequestMethod.PUT)
    public SongDTO updateSong(@RequestBody Song song) {
        return songService.convertEntityToDto(songService.updateSong(song));
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSong(@PathVariable("id") Long id) {
        songService.deleteSongById(id);
    }

    // GET songs by category
    @RequestMapping(value = "/songsByCat/{idCat}", method = RequestMethod.GET)
    public List<SongDTO> getSongsByCatId(@PathVariable("idCat") Long idCat) {
        return songService.findByCategorieIdCat(idCat)
                .stream()
                .map(songService::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
