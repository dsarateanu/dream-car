package com.home.dreamcar.service;

import com.home.dreamcar.exception.ErrorAdvice;
import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Offer;
import com.home.dreamcar.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceDefault implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public Offer saveOrUpdateOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer find(Long id) {
        return offerRepository.findById(id);
    }

    public Iterable<Offer> findAll() {
        return offerRepository.findAll();
    }

    public void delete(Long id) {
        Offer offer = find(id);
        if (offer != null) {
            offerRepository.delete(id);
        } else {
            throw new ErrorAdvice.NotModifiedDataAccessException("Product already gone");
        }
    }

    @Override
    public Iterable<Offer> findByAuction(Auction auction) {
        return offerRepository.findByAuction(auction);
    }

}
