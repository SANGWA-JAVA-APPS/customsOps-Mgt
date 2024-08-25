package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_exit_note;
@Repository
public interface Exit_noteRepository extends JpaRepository<Mdl_exit_note, Long> {
}

