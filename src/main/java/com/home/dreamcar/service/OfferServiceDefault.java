package com.home.dreamcar.service;

import com.home.dreamcar.exception.ErrorAdvice;
import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Offer;
import com.home.dreamcar.model.Status;
import com.home.dreamcar.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceDefault implements OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private EmailService emailService;

    public Offer saveOrUpdateOffer(Offer offer) {
        Offer offerSaved = offerRepository.save(offer);
        Auction auction = auctionService.find(offerSaved.getAuction().getId());
        if (auction.getTargetPricePerProduct() <= offerSaved.getPricePerProduct()) {
            List<Offer> offers = auction.getOffers();
            if (offers.size() != 1) {
                for (Offer offer1 : offers) {
                    offer1.setStatus(Status.LOST.toString());
                    saveOrUpdateOffer(offer1);
                }
            }
            offerSaved.setStatus(Status.WON.toString());
            saveOrUpdateOffer(offerSaved);
            auction.setStatus(Status.CLOSED.toString());
            auctionService.saveOrUpdateAuction(auction);
            emailService.sendMailToWinnerOfAuction(offerSaved.getUser().getEmail(), auction.getId(), auction.getProduct());
        }
        return offerSaved;
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
