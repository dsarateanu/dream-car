package com.home.dreamcar.service;

import com.home.dreamcar.exception.ErrorAdvice;
import com.home.dreamcar.model.Auction;
import com.home.dreamcar.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceDefault implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    public Auction saveOrUpdateAuction(Auction auction) {
        return auctionRepository.save(auction);
    }

    public Auction find(Long id) {
        return auctionRepository.findById(id);

    }

    public Iterable<Auction> findAll() {
        return auctionRepository.findAll();
    }

    @Override
    public Iterable<Auction> findAllByStatus(String status) {
        return auctionRepository.findByStatus(status);
    }

    public void delete(Long id) {
        Auction auction = find(id);
        if (auction != null) {
            auctionRepository.delete(id);
        } else {
            throw new ErrorAdvice.NotModifiedDataAccessException("Auction already gone");
        }

    }

}
