package com.esprit.pidevbackend.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@RequiredArgsConstructor


public class Discount {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long idPromotion ;
	String categorydiscount ;
	Integer discountpercentage ;
	@ManyToOne
	Achievements achievement ;
}
