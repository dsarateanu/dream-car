package com.home.dreamcar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "*Please provide your price per product")
    private Integer pricePerProduct;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Offer() {
    }

    public Offer(Auction auction, Integer pricePerProduct, User user) {
        this.auction = auction;
        this.pricePerProduct = pricePerProduct;
        this.user = user;
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

    public Integer getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(Integer pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
