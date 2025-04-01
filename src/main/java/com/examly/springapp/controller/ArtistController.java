package com.examly.springapp.controller;

import com.examly.springapp.model.Artist;
import com.examly.springapp.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity<Artist> saveArtist(@RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.saveArtist(artist), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@RequestBody Artist artist, @PathVariable Long id) {
        return ResponseEntity.ok(artistService.updateArtist(artist, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.ok("Artist deleted successfully");
    }
}