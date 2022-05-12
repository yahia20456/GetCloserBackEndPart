package com.esprit.pidevbackend.Service;

import com.esprit.pidevbackend.Domain.*;

import javax.mail.MessagingException;
import java.util.List;

public interface ICollaborationService {
    List<Collaboration> retrieveAllCollaborations();
    String addCollaboration(Collaboration c) throws InterruptedException;
    void deleteCollaboration(Long id);
    Collaboration updateCollaboration(Collaboration c);
    Collaboration retrieveCollaboration(Long id);

    //offer

    List<Offer> retrieveAllOffers();
    Offer addOffer(Offer o, long idCollaboration);
    void deleteOffer(Long id);
    Offer updateOffer(Offer o);
    Offer retrieveOffer(Long id);
    float calculProm(long idOffer);

    //publicity

    List<Publicity> retrieveAllPublicitys();
    Publicity addPublicity(Publicity p);
    void deletePublicity(Long id);
    Publicity updatePublicity(Publicity p);
    Publicity retrievePublicity(Long id);
    public boolean dateOffer();

    //reservation

    Reservation reservation(long idUser, long idOffer, Reservation r) throws MessagingException;
    float prixTotale(long idReservation);
    List<Reservation> findAll();
    List<Reservation> listAll() ;

    public int differenceStartDateAndEndDatePub(String dateStart, String dateEnd);
    public User findUserByUserName(String userName);
}
