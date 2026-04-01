package com.devoir1.moetezbenyemna.devoir1.controllers;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.devoir1.moetezbenyemna.devoir1.entities.Categories;
import com.devoir1.moetezbenyemna.devoir1.entities.Song;
import com.devoir1.moetezbenyemna.devoir1.service.SongService;

@Controller
public class SongController {

    @Autowired
    SongService songService;

    // Redirect root to list
    @GetMapping("/")
    public String welcome() {
        return "redirect:/ListeSongs";
    }

    // ── List with pagination ────────────────────────────────────────────────
    @RequestMapping("/ListeSongs")
    public String listeSongs(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size) {

        Page<Song> songs = songService.getAllSongsParPage(page, size);
        modelMap.addAttribute("songs", songs);
        modelMap.addAttribute("pages", new int[songs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);   // ← was missing
        return "listeSongs";
    }

    // ── Show create form ────────────────────────────────────────────────────
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        List<Categories> cats = songService.getAllCategories();
        modelMap.addAttribute("song", new Song());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("categories", cats);
        return "formSong";
    }

    // ── Save (create or update) ─────────────────────────────────────────────
    @RequestMapping("/saveSong")
    public String saveSong(@Valid @ModelAttribute("song") Song song,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            List<Categories> cats = songService.getAllCategories();
            modelMap.addAttribute("categories", cats);
            modelMap.addAttribute("mode", song.getIdSong() == null ? "new" : "edit");
            return "formSong";
        }

        boolean isNew = (song.getIdSong() == null);
        songService.saveSong(song);

        if (isNew) {
            // Go to last page so newly added song is visible
            Page<Song> songs = songService.getAllSongsParPage(page, size);
            page = Math.max(songs.getTotalPages() - 1, 0);
        }
        return "redirect:/ListeSongs?page=" + page + "&size=" + size;
    }

    // ── Delete ──────────────────────────────────────────────────────────────
    @RequestMapping("/supprimerSong")
    public String supprimerSong(@RequestParam("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size) {

        songService.deleteSongById(id);
        // If page is now empty, go back one
        Page<Song> remaining = songService.getAllSongsParPage(page, size);
        if (remaining.isEmpty() && page > 0) page--;
        return "redirect:/ListeSongs?page=" + page + "&size=" + size;
    }

    // ── Show edit form ──────────────────────────────────────────────────────
    @RequestMapping("/modifierSong")
    public String editerSong(@RequestParam("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            ModelMap modelMap) {

        Song song = songService.getSong(id);
        List<Categories> cats = songService.getAllCategories();
        modelMap.addAttribute("song", song);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("categories", cats);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("size", size);
        return "formSong";
    }
}
