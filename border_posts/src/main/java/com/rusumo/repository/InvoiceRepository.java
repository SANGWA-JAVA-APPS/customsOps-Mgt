package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_invoice;
@Repository
public interface InvoiceRepository extends JpaRepository<Mdl_invoice, Long> {
}

