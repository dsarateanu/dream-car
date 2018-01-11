package com.home.dreamcar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "target_price")
    @NotNull(message = "*Please provide target price")
    private Integer targetPrice;

    @Column(name = "currency")
    @NotEmpty(message = "*Please provide currency")
    private String currency;

    @Column(name = "expiration_date")
    @NotEmpty(message = "*Please provide expirationDate")
    private Date expirationDate;

    @Column(name = "number_of_products")
    @NotNull(message = "*Please provide number of products")
    private Integer numberOfProducts;

    @Column(name = "status")
    @NotEmpty(message = "*Please provide status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private Set<Offer> offers;

    public Auction() {
    }

    public Auction(Integer targetPrice, String currency, Date expirationDate, Integer numberOfProducts, String status, Product product) {
        this.targetPrice = targetPrice;
        this.currency = currency;
        this.expirationDate = expirationDate;
        this.numberOfProducts = numberOfProducts;
        this.status = status;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(Integer targetPrice) {
        this.targetPrice = targetPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
