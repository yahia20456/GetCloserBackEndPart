package com.esprit.pidevbackend.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Offer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long  idOffer;
	String title;
	String descrption;
	LocalDateTime starDateOf;
	LocalDateTime endDateOf;
	float nplaces;
	float promotion;
	int percentage;
	String location;
	float prix;
	int rate ;
	String picture;

	
	@Enumerated(EnumType.STRING)
	Happy happy;


	@ManyToOne
	Collaboration collaboration;

	@JsonIgnore
	@OneToMany(mappedBy="offers", cascade=CascadeType.ALL)
	private Set<Publicity>  publicity;



	




}
