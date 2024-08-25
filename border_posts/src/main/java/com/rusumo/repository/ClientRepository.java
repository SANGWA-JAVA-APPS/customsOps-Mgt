package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_client;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface ClientRepository extends JpaRepository<Mdl_client, Long> {
    
    @Query(value = "select * from client c where tin=?", nativeQuery = true)
    public Mdl_client findClientByTin(String tin);
}

