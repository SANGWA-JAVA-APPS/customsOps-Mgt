/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rusumo.repository;

import com.rusumo.models.Mdl_entry;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author HP
 */
public interface EntryRepository extends PagingAndSortingRepository<Mdl_entry, Long> {

     @Query(value = "select e.* from entry e"
            + " where date(e.date_time)>=? and date(e.date_time)<=?  ", nativeQuery = true)
    public List<Mdl_entry> findAllEntries(String startDate, String endDate);

    @Query(value = "select * from entry order by id desc limit 1", nativeQuery = true)
    public Mdl_entry getLastEntry();
}
