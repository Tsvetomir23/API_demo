package com.yetel.demo.Repositories;

import com.yetel.demo.Entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
        Optional<Provider> findProviderByName(String name);
}
