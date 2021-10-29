package com.github.fredO1211.BookingBackend.repository;

import com.github.fredO1211.BookingBackend.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query(nativeQuery = true, value = "SELECT count(*)>0 FROM FACILITIES WHERE NAME=:name")
    boolean isFacilityExists(String name);
}
