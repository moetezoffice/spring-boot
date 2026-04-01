package com.devoir1.moetezbenyemna.devoir1;

import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.devoir1.moetezbenyemna.devoir1.entities.Categories;
import com.devoir1.moetezbenyemna.devoir1.entities.Song;
import com.devoir1.moetezbenyemna.devoir1.repo.CategoriesRepository;
import com.devoir1.moetezbenyemna.devoir1.service.SongService;

@SpringBootApplication
public class Devoir1Application implements CommandLineRunner {

    @Autowired
    SongService songService;

    @Autowired
    CategoriesRepository categoriesRepository;

    public static void main(String[] args) {
        SpringApplication.run(Devoir1Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        // Only seed data if DB is empty (avoids duplicates on restart)
        if (songService.getAllSongs().isEmpty()) {
            // Create categories
            Categories pop = categoriesRepository.save(
                    new Categories(null, "Pop", "Musique pop internationale", null));
            Categories rnb = categoriesRepository.save(
                    new Categories(null, "R&B", "Rhythm and Blues", null));
            Categories rock = categoriesRepository.save(
                    new Categories(null, "Rock", "Rock classique et moderne", null));

            // Create songs
            songService.saveSong(new Song("Farewell feat. Eminem", 2600.0, new Date()));
            songService.saveSong(new Song("Killer", 2800.0, new Date()));
            songService.saveSong(new Song("Pretty Woman", 900.0, new Date()));
            songService.saveSong(new Song("Ocean Line", 2200.5, new Date()));
            songService.saveSong(new Song("Blinding Lights", 1500.0, new Date()));

            // Songs with categories
            Song s1 = new Song("Shape of You", 1200.0, new Date());
            s1.setCategorie(pop);
            songService.saveSong(s1);

            Song s2 = new Song("No Tears Left", 1800.0, new Date());
            s2.setCategorie(rnb);
            songService.saveSong(s2);

            Song s3 = new Song("Hotel California", 3000.0, new Date());
            s3.setCategorie(rock);
            songService.saveSong(s3);
        }
    }
}
