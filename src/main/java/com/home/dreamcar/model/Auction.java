package com.home.dreamcar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auction_id")
    private Long id;

    @Column(name = "target_price")
    @NotEmpty(message = "*Please provide target price")
    private Double targetPrice;

    @Column(name = "currency")
    @NotEmpty(message = "*Please provide currency")
    private Currency currency;

    @Column(name = "expiration_date")
    @NotEmpty(message = "*Please provide expirationDate")
    private Date expirationDate;

    @Column(name = "number_of_products")
    @NotEmpty(message = "*Please provide number of products")
    private Integer numberOfProducts;

    @Column(name = "status")
    @NotEmpty(message = "*Please provide status")
    private AuctionStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private Set<Offer> offers;

    public Auction() {
    }

    public Auction(Double targetPrice, Currency currency, Date expirationDate, Integer numberOfProducts, AuctionStatus status, User user, Product product) {
        this.targetPrice = targetPrice;
        this.currency = currency;
        this.expirationDate = expirationDate;
        this.numberOfProducts = numberOfProducts;
        this.status = status;
        this.user = user;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
