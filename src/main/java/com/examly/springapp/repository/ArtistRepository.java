package com.examly.springapp.repository;

import com.examly.springapp.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    
    // JPQL query to find artists by name containing (case insensitive)
    @Query("SELECT a FROM Artist a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Artist> findByNameContainingIgnoreCase(@Param("name") String name);
    
    // JPQL query to count artworks by artist
    @Query("SELECT COUNT(a) FROM Artwork a WHERE a.artist.id = :artistId")
    Long countArtworksByArtistId(@Param("artistId") Long artistId);
    
    // Corrected native query with named parameter
    @Query(value = "SELECT * FROM artist WHERE email LIKE ?1", nativeQuery = true)
    List<Artist> findByEmailContaining(String email);
}