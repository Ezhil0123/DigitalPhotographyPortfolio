package com.examly.springapp.controller;

import com.examly.springapp.model.Artwork;
import com.examly.springapp.service.ArtworkService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {

    private final ArtworkService artworkService;

    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @PostMapping
    public ResponseEntity<Artwork> saveArtwork(@RequestBody Artwork artwork) {
        return new ResponseEntity<>(artworkService.saveArtwork(artwork), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Artwork> getAllArtworks() {
        return artworkService.getAllArtworks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artwork> getArtworkById(@PathVariable Long id) {
        return ResponseEntity.ok(artworkService.getArtworkById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artwork> updateArtwork(@RequestBody Artwork artwork, @PathVariable Long id) {
        return ResponseEntity.ok(artworkService.updateArtwork(artwork, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtwork(@PathVariable Long id) {
        artworkService.deleteArtwork(id);
        return ResponseEntity.ok("Artwork deleted successfully");
    }

    @GetMapping("/paginated")
    public Page<Artwork> getPaginatedArtworks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return artworkService.findArtworksWithPaginationAndSorting(page, size, sortBy, direction);
    }
}