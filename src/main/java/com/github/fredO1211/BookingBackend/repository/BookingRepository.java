package com.github.fredO1211.BookingBackend.repository;

import com.github.fredO1211.BookingBackend.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM BOOKINGS " +
            "where START_OF_BOOKING >=:startDate and END_OF_BOOKING<=:endDate")
    List<Booking> findGuestByDate(String startDate, String endDate);

    @Query(nativeQuery = true, value = "SELECT count(*)<1 FROM BOOKINGS " +
            "WHERE ((START_OF_BOOKING >=:firstDate and START_OF_BOOKING<:lastDate) " +
            "or (END_OF_BOOKING >:firstDate and END_OF_BOOKING <=:lastDate)" +
            "or (START_OF_BOOKING <=:firstDate and END_OF_BOOKING >=:lastDate)) " +
            "and facility_id=:facilityId")
    boolean isAvailable(String firstDate, String lastDate, Long facilityId);

    @Query(nativeQuery = true, value = "SELECT count(*)<1 FROM BOOKINGS " +
            "WHERE (START_OF_BOOKING >=:date and END_OF_BOOKING<:date) " +
            "and facility_id=:facilityId")
    boolean isAvailable(String date,  Long facilityId);

    @Query(nativeQuery = true, value = "SELECT * FROM BOOKINGS " +
            "WHERE ((START_OF_BOOKING >=:firstDate and START_OF_BOOKING<:lastDate) " +
            "or (END_OF_BOOKING >:firstDate and END_OF_BOOKING <=:lastDate)" +
            "or (START_OF_BOOKING <=:firstDate and END_OF_BOOKING >=:lastDate)) " +
            "and facility_id=:facilityId")
    List<Booking> getBookingsBetweenDates(String firstDate, String lastDate, Long facilityId);
}
