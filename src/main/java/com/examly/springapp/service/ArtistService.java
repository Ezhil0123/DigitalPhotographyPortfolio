package com.examly.springapp.service;

import com.examly.springapp.model.Artist;
import com.examly.springapp.repository.ArtistRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ArtistService {
    
    Artist saveArtist(Artist artist);
    List<Artist> getAllArtists();
    Artist getArtistById(Long id);
    Artist updateArtist(Artist artist, Long id);
    void deleteArtist(Long id);

    @Service
    class ArtistServiceImpl implements ArtistService {
        
        private final ArtistRepository artistRepository;

        public ArtistServiceImpl(ArtistRepository artistRepository) {
            this.artistRepository = artistRepository;
        }

        @Override
        public Artist saveArtist(Artist artist) {
            return artistRepository.save(artist);
        }

        @Override
        public List<Artist> getAllArtists() {
            return artistRepository.findAll();
        }

        @Override
        public Artist getArtistById(Long id) {
            return artistRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Artist not found"));
        }

        @Override
        public Artist updateArtist(Artist artist, Long id) {
            Artist existingArtist = getArtistById(id);
            existingArtist.setName(artist.getName());
            existingArtist.setBio(artist.getBio());
            existingArtist.setEmail(artist.getEmail());
            return artistRepository.save(existingArtist);
        }

        @Override
        public void deleteArtist(Long id) {
            artistRepository.deleteById(id);
        }
    }
}