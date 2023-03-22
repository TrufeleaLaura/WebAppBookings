package com.projectFortech.ProjectFortech.repository;

import com.projectFortech.ProjectFortech.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
