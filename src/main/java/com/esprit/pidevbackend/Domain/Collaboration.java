package com.esprit.pidevbackend.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity

public class Collaboration implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCollaboration;
	private String name;
	private String description;
	private int phone;
	private String email;

	//
	String picture;

	String town;

	@JsonIgnore
	@OneToMany(mappedBy="collaboration", cascade=CascadeType.ALL)
	private Set<Offer> offers;
	@JsonIgnore
	@ManyToOne
	User users;

}
