package com.yetel.demo.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TaxesPayments")
public class TaxesPayments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double paymentAmount;

    private Date dateOfPayment;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client clients;

    public Client getClients() {
        return clients;
    }

    public void setClients(Client clients) {
        this.clients = clients;
    }


    @ManyToOne
    @JoinColumn(name = "providers_id")
    private Provider providers;

    public Provider getProvider() {
        return providers;
    }

    public void setProvider(Provider providers) {
        this.providers = providers;
    }



    public Long getId() {
        return id;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public TaxesPayments(Double paymentAmount, Date dateOfPayment) {
        this.paymentAmount = paymentAmount;
        this.dateOfPayment = dateOfPayment;
    }

    public TaxesPayments(){
    }



}
