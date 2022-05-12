package com.esprit.pidevbackend.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idReservation;
    LocalDateTime startDateRes;
    LocalDateTime endDateRes;
    float nmPalce;
    float priceTotal;


    @JsonIgnore
    @ManyToOne
    Offer offersRes;
    @JsonIgnore
    @ManyToOne
    User userRes;




}