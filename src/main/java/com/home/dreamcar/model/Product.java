package com.home.dreamcar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide product name")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "*Please provide product description")
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Auction> auctions;

    public Product() {
    }

    public Product(String name, String description, Set<Auction> auctions) {
        this.name = name;
        this.description = description;
        this.auctions = auctions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(Set<Auction> auctions) {
        this.auctions = auctions;
    }
}
