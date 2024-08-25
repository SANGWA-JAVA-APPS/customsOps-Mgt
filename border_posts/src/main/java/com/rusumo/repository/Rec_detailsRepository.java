package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_rec_details;
@Repository
public interface Rec_detailsRepository extends JpaRepository<Mdl_rec_details, Long> {
}

