package com.arnab.irrigation.repository;

import com.arnab.irrigation.domain.PlotConfiguration;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Repository
public interface PlotConfigurationRepository extends MongoRepository<PlotConfiguration, ObjectId> {
    
    List<PlotConfiguration> findByNextTimeSlotEquals(Date timeSlot);
    
}
