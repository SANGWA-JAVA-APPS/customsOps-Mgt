package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_inv_details;
@Repository
public interface Inv_detailsRepository extends JpaRepository<Mdl_inv_details, Long> {
}

