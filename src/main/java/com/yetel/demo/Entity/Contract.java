package com.yetel.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.PrimitiveIterator;
import java.util.Set;

@Entity
@Table(name = "contracts")
public class Contract {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numOfContract;

    private Date startDate;

    private Date endDate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "contracts_channels",
            joinColumns = @JoinColumn(name = "contracts_id"),
            inverseJoinColumns = @JoinColumn(name = "channels_id")
    )
    private Set<Channel> channels;

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "clients_id")
    private Client clients;

    public Client getClients() {
        return clients;
    }

    public void setClients(Client clients) {
        this.clients = clients;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "packages_id")
    private Packages packages;

    public Packages getPackages() {
        return packages;
    }

    public void setPackages(Packages packages) {
        this.packages = packages;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "providers_id")
    private Provider providers;

    public Provider getProvider() {
        return providers;
    }

    public void setProvider(Provider providers) {
        this.providers = providers;
    }

    public void setNumOfContract(Long numOfContract) {
        this.numOfContract = numOfContract;
    }

    public Long getId() {
        return id;
    }

    public Long getNumOfContract() {
        return numOfContract;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Contract(Long numOfContract, Date startDate, Date endDate) {
        this.numOfContract = numOfContract;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Contract() {
    }
}
