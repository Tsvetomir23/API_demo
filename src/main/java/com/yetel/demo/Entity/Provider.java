package com.yetel.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "providers")

    private Set<TaxesPayments> taxesPayments;

    public Set<TaxesPayments> getTaxesPayments() {
        return taxesPayments;
    }

    public void setTaxesPayments(Set<TaxesPayments> taxesPayments) {
        this.taxesPayments = taxesPayments;
    }

    public void addTaxesPayment(TaxesPayments taxesPayment)
    {
        taxesPayments.add(taxesPayment);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "providers")
    private Set<Contract> contracts;

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "providers_channel",
            joinColumns = @JoinColumn(name = "providers_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private Set<Channel> channels;

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public Provider() {
    }

    public Provider(String name, Double price) {
        this.name = name;
        this.price = price;

    }


}
