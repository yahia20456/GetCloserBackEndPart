package com.esprit.pidevbackend.Service;

import com.esprit.pidevbackend.Domain.Achievements;

import java.util.List;

public interface AchievementsService {

        public void addAchievementsandEvaluation(Long iduser);
        public Integer CalculateAndVerifyScore(Long IdUser, Integer score);
        List<Achievements> findAllOrderByScoreTotalAsc() ;
        List<Achievements> retrieveAllachievements() ;
        Achievements retrieveAchievementsById(Long idAchievements) ;
        public void deleteAchievements(Long id);
        public void updateAchievements(Long id ,Achievements achievements);

}
