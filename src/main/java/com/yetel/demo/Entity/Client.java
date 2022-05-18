package com.yetel.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.Set;


@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String egn;

    private String email;

    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL)
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
    public Client() {
    }

    @JsonIgnore
    @OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Client(String firstName, String lastName, String egn, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.egn = egn;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
