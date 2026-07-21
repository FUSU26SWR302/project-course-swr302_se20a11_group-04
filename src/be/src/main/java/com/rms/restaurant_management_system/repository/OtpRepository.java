package com.rms.restaurant_management_system.repository;

import com.rms.restaurant_management_system.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findTopByAccountAndIsUsedFalseOrderByCreatedAtDesc(String account);
}
