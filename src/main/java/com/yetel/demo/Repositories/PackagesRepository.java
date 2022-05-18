package com.yetel.demo.Repositories;

import com.yetel.demo.Entity.Packages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PackagesRepository extends JpaRepository<Packages, Long> {

    Optional<Packages> findPackagesByName(String name);

}
