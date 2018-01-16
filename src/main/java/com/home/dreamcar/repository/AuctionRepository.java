package com.home.dreamcar.repository;

import com.home.dreamcar.model.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, Long> {

    Auction save(Auction auction);

    void delete(Long id);

    Auction findById(Long id);

    Iterable<Auction> findByStatus(String status);

}
