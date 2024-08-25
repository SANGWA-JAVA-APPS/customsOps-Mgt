package com.rusumo.repository;

import com.rusumo.dto.DTOTally;
import com.rusumo.dto.DTO_ArrivcalTally;
import com.rusumo.models.Mdl_tallying;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TallyingRepository extends JpaRepository<Mdl_tallying, Long> {

    @Query(value = "select a.id as id, min(t.account_id) account_id ,a.seal_no ,  max(t.date_time) date_time, max(t.description) description, "
            + " count(t.item_name) item_name,   sum(t.man_qty) man_qty, sum(t.man_weight) man_weight, sum(t.actual_qty) actual_qty, sum(t.actual_wgh) actual_wgh, min(t.cont_no) cont_no   ,count(t.arriv_tallying)  arriv_tallying\n"
            + "  , count(a.entry_arrival) entry_arrival \n"
            + " from tallying t \n"
            + "  join arrival a on a.id = t.arriv_tallying "
            + "  join entry e on e.id = a.entry_arrival "
            + "  where    date(t.date_time)>=?  and date(t.date_time) <=?   \n"
            + " group by a.id , a.seal_no "
            + " order by  a.id asc", nativeQuery = true)
    public List<Mdl_tallying> findSummarizedTallying(Date startDate, Date endDate);

    @Query(value = "select t.* from tallying t "
            + " where  date(t.date_time)>=?  and date(t.date_time)<=? ", nativeQuery = true)
    public List<Mdl_tallying> findAllTallyings(Date startDate, Date endDate);

    @Query(value = "select * from tallying order by id desc limit 1", nativeQuery = true)
    public Mdl_tallying getLastArrival();

    @Query(value = "select arriv_tallying as id,   t.account_id  ,a.seal_no ,   date(t.date_time) as date_time ,  t.description  description, "
            + " t.item_name ,   t.man_qty, t.man_weight , t.actual_qty actual_qty, t.actual_wgh as actual_wgh, t.cont_no    ,t.arriv_tallying  arriv_tallying\n"
            + "  , a.entry_arrival  \n"
            + "  from tallying t \n"
            + ""
            + "  where  month(t.date_time)= ? and year(t.date_time)= ?   \n"
            + " order by a.id asc", nativeQuery = true)
    public List<Mdl_tallying> findMappedTally(int month, int year);

    @Query("select new com.rusumo.dto.DTOTally ( t.id, t.item_name, t.date_time , t.description, t.man_qty, t.man_weight, t.actual_qty, t.actual_wgh, t.cont_no, t.account_id, c.name, c.surname, c.tin,"
            + "   a.seal_no , a.id as arriv_no )"
            + " FROM Mdl_tallying  t "
            + " join t.mdl_arrival a "
            + " join a.mdl_entry e "
            + " join e.mdl_client c "
            + " WHERE month(t.date_time)= ?1 and year(t.date_time)= ?2 "
            + " order by a.id asc")
    public List<DTOTally> findCurrentMonthTallying(int month, int year);

    @Query("select new com.rusumo.dto.DTO_ArrivcalTally("
            + "a.id, a.seal_no,"
            + ""
            + "  e.country,   e.ddcom,    e.plate_no,       e.clearing_agent,   e.country_dest,"
            + ""
            + "  t.item_name,"
            + "  t.date_time,   t.description,  t.man_qty,  t.man_weight,  t.actual_qty,  t.actual_wgh,     t.cont_no ,   "
            + ""
            + "  c.name,   c.surname,   c.telephone)    "
            + "  from Mdl_tallying t    right join  t.mdl_arrival a"
            + "  join  a.mdl_entry e"
            + "  "
            + "  join  e.mdl_client c"
            + "  where date(e.date_time)>=?1 and date(e.date_time)<=?2   "
            + " ")
    public List<DTO_ArrivcalTally> findEntriesArrivalTally(Date startDate, Date endDate);

}
