package com.home.dreamcar.service;

import com.home.dreamcar.model.Auction;

public interface AuctionService {

    Auction saveOrUpdateAuction(Auction auction);

    Auction find(Long id);

    Iterable<Auction> findAll();

    Iterable<Auction> findAllByStatus(String status);

    void delete(Long id);
}
