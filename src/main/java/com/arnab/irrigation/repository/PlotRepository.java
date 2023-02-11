package com.arnab.irrigation.repository;

import com.arnab.irrigation.domain.Plot;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Arnab Bhattacharyya
 */
@Repository
public interface PlotRepository extends MongoRepository<Plot, ObjectId> {
    Optional<Plot> findByCode(String code);

    @Query("{ '_id' : ?0 }")
    Optional<Plot> findById(ObjectId id);

}
