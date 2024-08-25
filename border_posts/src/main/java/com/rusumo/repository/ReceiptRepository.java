package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_receipt;
@Repository
public interface ReceiptRepository extends JpaRepository<Mdl_receipt, Long> {
}

