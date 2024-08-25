package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_account_category;
@Repository
public interface Account_categoryRepository extends JpaRepository<Mdl_account_category, Long> {
    
}

