package com.home.dreamcar.service;

import com.home.dreamcar.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceDefault {

    @Autowired
    private AuctionRepository auctionRepository;

}
