package com.esprit.pidevbackend.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Achievements {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long idAchievements ;
    Integer score ;
    Integer trophee ;
    @OneToOne

    private User user ;
    @OneToOne

    private Evaluation evaluation ;

    public Achievements(Integer progressionLevel, Integer score, Integer trophee) {
        super();
        this.score = score;
        this.trophee = trophee;
    }


}
