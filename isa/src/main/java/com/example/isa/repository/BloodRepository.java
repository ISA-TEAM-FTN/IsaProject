package com.example.isa.repository;

import com.example.isa.model.Blood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRepository extends JpaRepository<Blood, Long> {
}
