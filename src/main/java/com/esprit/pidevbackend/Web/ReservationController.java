package com.esprit.pidevbackend.Web;


import com.esprit.pidevbackend.API.StripeService;
import com.esprit.pidevbackend.Domain.Payment;
import com.esprit.pidevbackend.Domain.Reservation;
import com.esprit.pidevbackend.Domain.User;
import com.esprit.pidevbackend.Repository.UserRepository;
import com.esprit.pidevbackend.Service.ICollaborationService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RequestMapping("/Reservation")
@RestController

public class ReservationController {
    @Autowired
    ICollaborationService reservationService;

    @Autowired
    StripeService stripeService;

    @Autowired
    UserRepository userRepository;

    //@Secured({"ROLE_USER","ROLE_ADMIN"})
    //http://localhost:8085/Reservation/addResevation/1/1
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addResevation/{idOffer}/{idUser}")
    @ResponseBody
    public void addReservation(@RequestBody Reservation r, @PathVariable long idUser, @PathVariable long idOffer) throws MessagingException {
        reservationService.reservation(idUser,idOffer,r);
    }
    //@Secured({"ROLE_USER","ROLE_ADMIN"})
    //http://localhost:8085/Reservation/calculTotal/1/1
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/calculTotal/{idReservation}")
    @ResponseBody
    public float calculTotal( @PathVariable long idReservation){
        return reservationService.prixTotale(idReservation);
    }

    //http://localhost:8085/Reservation/1/stripe
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("stripe/{idUser}/{idReservation}")
    @ResponseBody
    public Payment index(@PathVariable long idUser , @PathVariable long idReservation,@RequestBody Payment p ) throws StripeException {
        return stripeService.payment(idUser,idReservation,p);
    }

    // @Secured({"ROLE_USER","ROLE_ADMIN"})
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/stripe/{email}/{token}/{idUser}/{idReservation}/{idOffer}")
    @ResponseBody
    public Reservation createCharge(@PathVariable String email, String token,@PathVariable Long idUser,@PathVariable Long idReservation,@PathVariable Long idOffer,@RequestBody Reservation r) throws StripeException, MessagingException {
        return stripeService.createCharge(email,token,idUser,idReservation,idOffer,r);
    }
   // @Secured({"ROLE_USER","ROLE_ADMIN"})
   @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/difference")
    @ResponseBody
    public int differenceStartDateAndEndDatePub(String dateStart,String dateEnd) {
        return reservationService.differenceStartDateAndEndDatePub(dateStart,dateEnd);
    }

    @GetMapping("UserByUserName/{userName}")
    public User findUserByUserName(@PathVariable("userName") String userName) {
        return reservationService.findUserByUserName(userName);
    }

}
