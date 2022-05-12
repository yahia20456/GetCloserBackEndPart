package com.esprit.pidevbackend.Service;

import com.esprit.pidevbackend.Domain.User;

public interface EvaluationService {
    public void AffectActivityBadge(Long IdUser, Integer A);
    public void IncrementGamesPoints(Long IdUser);
    public void IncrementQuizPoints(Long IdUser, Integer QuizResult);
    public void IncrementActivityPointsLike(Long IdUser);
    public void IncrementActivityPointsComment(Long IdUser);
    public void DecrementActivityPointsUnlike(Long IdUser);
    public void DecrementActivityPointsUncomment(Long IdUser);
}
