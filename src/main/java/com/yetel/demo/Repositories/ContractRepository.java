package com.yetel.demo.Repositories;

import com.yetel.demo.Entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findContractByNumOfContract(Long NumOfContract);
}
