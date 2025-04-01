package com.examly.springapp.repository;

import com.examly.springapp.model.Artwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    
    // JPQL query to find artworks by title containing (case insensitive)
    @Query("SELECT a FROM Artwork a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Artwork> findByTitleContainingIgnoreCase(String title);
    
    // JPQL query to find artworks by price range
    @Query("SELECT a FROM Artwork a WHERE a.price BETWEEN :minPrice AND :maxPrice")
    List<Artwork> findByPriceRange(double minPrice, double maxPrice);
    
    // JPQL query to find artworks by artist ID with pagination
    @Query("SELECT a FROM Artwork a WHERE a.artist.id = :artistId")
    Page<Artwork> findByArtistId(Long artistId, Pageable pageable);
    
    // JPQL query to find most expensive artworks
    @Query("SELECT a FROM Artwork a ORDER BY a.price DESC")
    List<Artwork> findTopExpensiveArtworks(Pageable pageable);
}