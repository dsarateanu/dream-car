package com.home.dreamcar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @Column(name = "price_per_product")
    @NotEmpty(message = "*Please provide your price per product")
    private Double pricePerProduct;

    public Offer() {
    }

    public Offer(Auction auction, Double pricePerProduct) {
        this.auction = auction;
        this.pricePerProduct = pricePerProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Double getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(Double pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }
}
