package com.esprit.pidevbackend.Repository;

import com.esprit.pidevbackend.Domain.Reservation;
import com.esprit.pidevbackend.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface IReservation  extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByStartDateResIsBefore(LocalDateTime date);

    @Query("select x from User  x where x.username=:userName ")
    public User findUserByUserName(@Param("userName") String userName) ;
}
