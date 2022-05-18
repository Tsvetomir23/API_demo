package com.yetel.demo.Repositories;

import com.yetel.demo.Entity.TaxesPayments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TaxesPaymentsRepository extends JpaRepository <TaxesPayments, Long>{
    Optional<TaxesPayments> findTaxesPaymentsByDateOfPayment(Date dateOfPayment);
}
