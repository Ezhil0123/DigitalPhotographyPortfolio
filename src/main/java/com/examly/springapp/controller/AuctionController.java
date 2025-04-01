package com.examly.springapp.controller;

import com.examly.springapp.model.Auction;
import com.examly.springapp.service.AuctionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping
    public ResponseEntity<Auction> saveAuction(@RequestBody Auction auction) {
        return new ResponseEntity<>(auctionService.saveAuction(auction), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long id) {
        return ResponseEntity.ok(auctionService.getAuctionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auction> updateAuction(@RequestBody Auction auction, @PathVariable Long id) {
        return ResponseEntity.ok(auctionService.updateAuction(auction, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
        return ResponseEntity.ok("Auction deleted successfully");
    }

    @GetMapping("/active")
    public List<Auction> getActiveAuctions() {
        return auctionService.findActiveAuctions();
    }

    @GetMapping("/artwork/{artworkId}")
    public List<Auction> getAuctionsByArtwork(@PathVariable Long artworkId) {
        return auctionService.findAuctionsByArtwork(artworkId);
    }
}