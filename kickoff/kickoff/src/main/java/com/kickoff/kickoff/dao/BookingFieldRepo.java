package com.kickoff.kickoff.dao;

import com.kickoff.kickoff.entity.BookingField;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookingFieldRepo extends JpaRepository<BookingField, Integer> {

    @Query(value = "SELECT * FROM Booking_Field WHERE date = ?1 AND time = ?2 AND field_id = ?3", nativeQuery = true)
    public Iterable<BookingField> findDateTime(String date, String time, int field_id);

    @Query(value = "SELECT * FROM Booking_Field WHERE date = ?1 AND field_id = ?2", nativeQuery = true)
    public Iterable<BookingField> findDateField(String date, int field_id);

    @Query(value = "SELECT * FROM Booking_Field WHERE time = ?1", nativeQuery = true)
    public Iterable<BookingField> findTime(String time);

    @Query(value = "SELECT * FROM Booking_Field WHERE user_id = ?1", nativeQuery = true)
    public Iterable<BookingField> findUserProps(int userId);

}