package com.examly.springapp.service;

import com.examly.springapp.model.Auction;
import com.examly.springapp.repository.AuctionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AuctionService {
    
    Auction saveAuction(Auction auction);
    List<Auction> getAllAuctions();
    Auction getAuctionById(Long id);
    Auction updateAuction(Auction auction, Long id);
    void deleteAuction(Long id);
    List<Auction> findActiveAuctions();
    List<Auction> findAuctionsByArtwork(Long artworkId);

    @Service
    class AuctionServiceImpl implements AuctionService {
        
        private final AuctionRepository auctionRepository;

        public AuctionServiceImpl(AuctionRepository auctionRepository) {
            this.auctionRepository = auctionRepository;
        }

        @Override
        public Auction saveAuction(Auction auction) {
            return auctionRepository.save(auction);
        }

        @Override
        public List<Auction> getAllAuctions() {
            return auctionRepository.findAll();
        }

        @Override
        public Auction getAuctionById(Long id) {
            return auctionRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Auction not found"));
        }

        @Override
        public Auction updateAuction(Auction auction, Long id) {
            Auction existingAuction = getAuctionById(id);
            existingAuction.setTitle(auction.getTitle());
            existingAuction.setStartDate(auction.getStartDate());
            existingAuction.setEndDate(auction.getEndDate());
            existingAuction.setStartingPrice(auction.getStartingPrice());
            existingAuction.setCurrentBid(auction.getCurrentBid());
            return auctionRepository.save(existingAuction);
        }

        @Override
        public void deleteAuction(Long id) {
            auctionRepository.deleteById(id);
        }

        @Override
        public List<Auction> findActiveAuctions() {
            LocalDateTime now = LocalDateTime.now();
            return auctionRepository.findByStartDateBeforeAndEndDateAfter(now, now);
        }

        @Override
        public List<Auction> findAuctionsByArtwork(Long artworkId) {
            return auctionRepository.findByArtworkId(artworkId);
        }
    }
}