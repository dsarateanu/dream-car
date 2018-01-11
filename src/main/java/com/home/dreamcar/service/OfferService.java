package com.home.dreamcar.service;

import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Offer;

public interface OfferService {

    Offer find(Long id);

    Offer saveOrUpdateOffer(Offer offer);

    public Iterable<Offer> findAll();

    void delete(Long id);

    Iterable<Offer> findByAuction(Auction auction);
}
