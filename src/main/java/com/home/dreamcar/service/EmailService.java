package com.home.dreamcar.service;

import com.home.dreamcar.model.Product;

public interface EmailService {

    public void sendMailToWinnerOfAuction(String to, Long auctionId, Product product);

    public void sendMailToActivatedUser(String to);

    public void sendMailToUnactivatedUser(String to);
}
