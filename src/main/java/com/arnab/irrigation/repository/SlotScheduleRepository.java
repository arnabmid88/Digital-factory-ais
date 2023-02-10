package com.arnab.irrigation.repository;

import com.arnab.irrigation.domain.SlotSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Repository
public interface SlotScheduleRepository extends MongoRepository<SlotSchedule, Long> {
    
    
    
}
