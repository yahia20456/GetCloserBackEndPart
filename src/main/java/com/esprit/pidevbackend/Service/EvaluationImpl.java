package com.esprit.pidevbackend.Service;

import com.esprit.pidevbackend.Domain.Badge;
import com.esprit.pidevbackend.Domain.User;
import com.esprit.pidevbackend.Repository.IAchievementsRepository;
import com.esprit.pidevbackend.Repository.IEvaluationRepository;
import com.esprit.pidevbackend.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationImpl implements EvaluationService {
    @Autowired
    IEvaluationRepository EvaluationRepo ;
    @Autowired
    IAchievementsRepository achievementRepo ;
    @Autowired
    UserRepository UserRepo ;
    public void IncrementActivityPointsLike(Long IdUser) {
        User user = UserRepo.findById(IdUser).orElse(null);
        user.getAchievements().getEvaluation().setActivitypoints(user.getAchievements().getEvaluation().getActivitypoints()+10);
        UserRepo.save(user);
    }
    public void IncrementActivityPointsComment(Long IdUser) {
        User user = UserRepo.findById(IdUser).orElse(null);
        user.getAchievements().getEvaluation().setActivitypoints(user.getAchievements().getEvaluation().getActivitypoints()+20);
        UserRepo.save(user);
    }
    public void DecrementActivityPointsUnlike(Long IdUser) {
        User user = UserRepo.findById(IdUser).orElse(null);
        user.getAchievements().getEvaluation().setActivitypoints(user.getAchievements().getEvaluation().getActivitypoints()-10);
        UserRepo.save(user);
    }
    @Override
    public void DecrementActivityPointsUncomment(Long IdUser) {
        User user = UserRepo.findById(IdUser).orElse(null);
        user.getAchievements().getEvaluation().setActivitypoints(user.getAchievements().getEvaluation().getActivitypoints()-20);
        UserRepo.save(user);

    }
    @Override
    public void IncrementQuizPoints(Long IdUser,Integer QuizResult) {

        User user = UserRepo.findById(IdUser).orElse(null);
        user.getAchievements().getEvaluation().setQuizpoints(user.getAchievements().getEvaluation().getQuizpoints()+ QuizResult);
        UserRepo.save(user);
    }
    @Override
    public void IncrementGamesPoints(Long IdUser) {

        User user = UserRepo.findById(IdUser).orElse(null);
        user.getAchievements().getEvaluation().setGamespoints(user.getAchievements().getEvaluation().getGamespoints()+ 30);
        UserRepo.save(user);
    }
    @Override
    public void AffectActivityBadge(Long IdUser,Integer A) {
        User user = UserRepo.findById(IdUser).orElse(null);
        A = user.getAchievements().getEvaluation().getActivitypoints();
        if (A>=0 && A<=50){
            user.getAchievements().getEvaluation().setActivitybadge(Badge.None);
        }
        if (A>50){
            user.getAchievements().getEvaluation().setActivitybadge(Badge.Silver);
        }
        if (A>100){
            user.getAchievements().getEvaluation().setActivitybadge(Badge.Gold);
        }
        if(A>170){
            user.getAchievements().getEvaluation().setActivitybadge(Badge.Platinum);
        }
        if(A>300){
            user.getAchievements().getEvaluation().setActivitybadge(Badge.Diamond);
        }
        if(A>500){
            user.getAchievements().getEvaluation().setActivitybadge(Badge.Legendary);
        }

        UserRepo.save(user);



    }

}
