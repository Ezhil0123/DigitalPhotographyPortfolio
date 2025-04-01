package com.examly.springapp.repository;

import com.examly.springapp.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    
    // JPQL query to find active auctions (current time between start and end dates)
    @Query("SELECT a FROM Auction a WHERE a.startDate <= :currentTime AND a.endDate >= :currentTime")
    List<Auction> findActiveAuctions(LocalDateTime currentTime);
    
    // JPQL query to find auctions by artwork ID
    @Query("SELECT a FROM Auction a WHERE a.artwork.id = :artworkId")
    List<Auction> findByArtworkId(Long artworkId);
    
    // JPQL query to find auctions ending soon
    @Query("SELECT a FROM Auction a WHERE a.endDate BETWEEN :now AND :endDate ORDER BY a.endDate ASC")
    List<Auction> findAuctionsEndingSoon(LocalDateTime now, LocalDateTime endDate);
    
    // JPQL query to update current bid
    @Query("UPDATE Auction a SET a.currentBid = :newBid WHERE a.id = :auctionId")
    void updateCurrentBid(Long auctionId, Double newBid);

    List<Auction> findByStartDateBeforeAndEndDateAfter(LocalDateTime now, LocalDateTime now2);
}