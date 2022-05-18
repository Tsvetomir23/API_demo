package com.yetel.demo.Repositories;

import com.yetel.demo.Entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findChannelByName(String name);
}
