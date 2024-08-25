package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_account;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountRepository extends JpaRepository<Mdl_account, Long> {

    @Query(value = "select id,username, password, profile, acc_category, profile_id "
            + " from account"
            + "  where username=? and password=? ", nativeQuery = true)
    Mdl_account findAccountByUsernamePassword(String username, String password);
}
