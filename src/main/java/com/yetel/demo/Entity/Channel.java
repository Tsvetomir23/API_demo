package com.yetel.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    public Channel(String name, Double price) {
        this.name = name;
        this.price = price;
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

    public Channel() {
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "channel_package",
            joinColumns = @JoinColumn(name = "channels_id"),
            inverseJoinColumns = @JoinColumn(name = "packages_id")
    ) private Set<Packages> Package;

    public Set<Packages> getPackage() {
        return Package;
    }

    public void setPackage(Set<Packages> Package) {
        this.Package = Package;
    }
}
