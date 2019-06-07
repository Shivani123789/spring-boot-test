package com.stackroute.muzixservice.repository;


import com.stackroute.muzixservice.domain.Muzix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MuzixRepository extends JpaRepository<Muzix,Integer> {
@Query("select u from Muzix u where u.trackName=:trackName")
    public Muzix displayTrackByName(@Param("trackName") String trackName);
}
