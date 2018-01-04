package com.home.dreamcar.model;

public enum AuctionStatus {

    ACTIVE("ACTIVE"),
    DELETED("DELETED"),
    COMPLETED("COMPLETED");

    private String status;

    AuctionStatus() {
    }

    AuctionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
