package com.home.dreamcar.config;

import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Offer;
import com.home.dreamcar.model.Status;
import com.home.dreamcar.service.AuctionService;
import com.home.dreamcar.service.EmailService;
import com.home.dreamcar.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
public class AuctionScheduledTasks {

    @Autowired
    EmailService emailService;
    @Autowired
    AuctionService auctionService;
    @Autowired
    OfferService offerService;

    private static final Logger log = LoggerFactory.getLogger(AuctionScheduledTasks.class);

    //@Scheduled(cron ="0 0 0 * * * *")
    @Scheduled(cron = "0 */2 * * * *")
    public void checkAuction() {
        log.info("Check auction");
        Iterable<Auction> auctions = auctionService.findAll();
        if (auctions != null) {
            Date date = new Date();
            for (Auction auction : auctions) {
                if (auction.getStatus().equals(Status.ACTIVE.toString()) && date.after(auction.getExpirationDate())) {
                    log.info("found expired auction");
                    Offer winningOffer = null;
                    List<Offer> offers = auction.getOffers();
                    if (offers.size() > 0) {
                        if (offers.size() == 1) {
                            log.info("one offer");
                            winningOffer = offers.get(0);
                        } else {
                            log.info("multiple offers");
                            for (Offer offer : offers) {
                                offer.setStatus(Status.LOST.toString());
                                offerService.saveOrUpdateOffer(offer);
                            }
                            final Comparator<Offer> comparator = Comparator.comparingDouble(Offer::getPricePerProduct);
                            winningOffer = offers.stream().max(comparator).get();
                        }
                        winningOffer.setStatus(Status.WON.toString());
                        offerService.saveOrUpdateOffer(winningOffer);
                        auction.setStatus(Status.CLOSED.toString());
                        auctionService.saveOrUpdateAuction(auction);
                        emailService.sendMailToWinnerOfAuction(winningOffer.getUser().getEmail(), auction.getId(), auction.getProduct());
                    } else {
                        log.info("no offers");
                    }
                }
            }
        }
    }

}
