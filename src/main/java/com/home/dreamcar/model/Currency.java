package com.home.dreamcar.model;

public enum Currency {

    USD("USD"),
    RON("RON"),
    EUR("EUR");

    private String currency;

    Currency() {
    }

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
