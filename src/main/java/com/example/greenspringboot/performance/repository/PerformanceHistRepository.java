package com.example.greenspringboot.performance.repository;
import com.example.greenspringboot.performance.entity.PerformanceHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceHistRepository extends JpaRepository<PerformanceHist, Integer> {
}
