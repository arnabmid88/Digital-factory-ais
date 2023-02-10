package com.arnab.irrigation.repository;

import com.arnab.irrigation.domain.Plot;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Repository
public interface PlotRepository extends MongoRepository<Plot, Long> {
    Optional<Plot> findByCode(String code);

}
