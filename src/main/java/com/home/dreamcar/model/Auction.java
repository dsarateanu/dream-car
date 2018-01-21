package com.home.dreamcar.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "target_price")
    @NotNull(message = "*Please provide target price")
    private Double targetPricePerProduct;

    @Column(name = "currency")
    @NotNull(message = "*Please provide currency")
    private String currency;

    @Column(name = "expiration_date")
    @NotNull(message = "*Please provide expirationDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;

    @Column(name = "number_of_products")
    @NotNull(message = "*Please provide number of products")
    private Integer numberOfProducts;

    @Column(name = "status")
    @NotNull(message = "*Please provide status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Offer> offers;

    public Auction() {
    }

    public Auction(Double targetPricePerProduct, String currency, Date expirationDate, Integer numberOfProducts, String status, Product product, List<Offer> offers) {
        this.targetPricePerProduct = targetPricePerProduct;
        this.currency = currency;
        this.expirationDate = expirationDate;
        this.numberOfProducts = numberOfProducts;
        this.status = status;
        this.product = product;
        this.offers = offers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTargetPricePerProduct() {
        return targetPricePerProduct;
    }

    public void setTargetPricePerProduct(Double targetPricePerProduct) {
        this.targetPricePerProduct = targetPricePerProduct;
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

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
