package com.devoir1.moetezbenyemna.devoir1;

import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import com.devoir1.moetezbenyemna.devoir1.entities.Song;
import com.devoir1.moetezbenyemna.devoir1.repo.SongRepository;
import com.devoir1.moetezbenyemna.devoir1.service.SongService;

@SpringBootTest
class Devoir1ApplicationTests {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    @Test
    public void testCreateSong() {
        Song song = new Song("Ocean Line", 2200.5, new Date());
        songRepository.save(song);
        System.out.println("Song saved: " + song);
    }

    @Test
    public void testGetAllSongsParPage() {
        Page<Song> songs = songService.getAllSongsParPage(0, 2);
        System.out.println("Size per page: " + songs.getSize());
        System.out.println("Total elements: " + songs.getTotalElements());
        System.out.println("Total pages: " + songs.getTotalPages());
        for (Song s : songs) {
            System.out.println(s);
        }
    }

    @Test
    public void testFindByNomContains() {
        List<Song> songs = songService.findByNomSongContains("Killer");
        songs.forEach(System.out::println);
    }

    @Test
    public void testFindByCatId() {
        List<Song> songs = songService.findByCategorieIdCat(1L);
        songs.forEach(System.out::println);
    }
}
