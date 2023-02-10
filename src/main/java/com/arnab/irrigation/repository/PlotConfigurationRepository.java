package com.arnab.irrigation.repository;

import com.arnab.irrigation.domain.PlotConfiguration;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Repository
public interface PlotConfigurationRepository extends MongoRepository<PlotConfiguration, Long> {
    
    List<PlotConfiguration> findByNextTimeSlotEquals(Date timeSlot);
    
}
