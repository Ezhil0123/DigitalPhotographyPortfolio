package com.examly.springapp.service;

import com.examly.springapp.model.Artwork;
import com.examly.springapp.repository.ArtworkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ArtworkService {
    
    Artwork saveArtwork(Artwork artwork);
    List<Artwork> getAllArtworks();
    Artwork getArtworkById(Long id);
    Artwork updateArtwork(Artwork artwork, Long id);
    void deleteArtwork(Long id);
    Page<Artwork> findArtworksWithPaginationAndSorting(int page, int size, String field, String direction);

    @Service
    class ArtworkServiceImpl implements ArtworkService {
        
        private final ArtworkRepository artworkRepository;

        public ArtworkServiceImpl(ArtworkRepository artworkRepository) {
            this.artworkRepository = artworkRepository;
        }

        @Override
        public Artwork saveArtwork(Artwork artwork) {
            return artworkRepository.save(artwork);
        }

        @Override
        public List<Artwork> getAllArtworks() {
            return artworkRepository.findAll();
        }

        @Override
        public Artwork getArtworkById(Long id) {
            return artworkRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Artwork not found"));
        }

        @Override
        public Artwork updateArtwork(Artwork artwork, Long id) {
            Artwork existingArtwork = getArtworkById(id);
            existingArtwork.setTitle(artwork.getTitle());
            existingArtwork.setDescription(artwork.getDescription());
            existingArtwork.setPrice(artwork.getPrice());
            return artworkRepository.save(existingArtwork);
        }

        @Override
        public void deleteArtwork(Long id) {
            artworkRepository.deleteById(id);
        }

        @Override
        public Page<Artwork> findArtworksWithPaginationAndSorting(int page, int size, String field, String direction) {
            Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
            PageRequest pageRequest = PageRequest.of(page, size, sort);
            return artworkRepository.findAll(pageRequest);
        }
    }
}