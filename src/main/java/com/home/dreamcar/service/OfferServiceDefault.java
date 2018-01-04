package com.home.dreamcar.service;

import com.home.dreamcar.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceDefault {

    @Autowired
    private OfferRepository offerRepository;
}
