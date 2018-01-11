package com.home.dreamcar.repository;

import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

    Offer save(Offer offer);

    void delete(Long id);

    Offer findById(Long id);

    Iterable<Offer> findByAuction(Auction auction);
}
