package com.rusumo.repository;

import com.rusumo.dto.DTO_ArrivalNotices;
import com.rusumo.models.Mdl_arrival;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalRepository extends JpaRepository<Mdl_arrival, Long> {
    @Query("select new com.rusumo.dto.DTO_ArrivalNotices("
            + "  a.id, a.seal_no,"
            + "  c.name,   c.surname,   c.telephone, c.tin,"
            + "  e.country,   e.ddcom,    e.plate_no,       e.clearing_agent,   e.country_dest ,e.date_time)"
            + "     from Mdl_arrival a       "
            + "  join  a.mdl_entry e"
            + "  join  e.mdl_client c"
            + "  where date(e.date_time)>=?1 and date(e.date_time)<=?2 "
            + " order by a.id asc  "
            + " ")
    public List<DTO_ArrivalNotices> findEntriesArrival(Date startDate, Date endDate);


    @Query(value = "select * from arrival order by id desc limit 1", nativeQuery = true)
    public Mdl_arrival getLastArrival();



}
