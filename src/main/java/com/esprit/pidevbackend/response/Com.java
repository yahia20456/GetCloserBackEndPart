package com.esprit.pidevbackend.response;

import com.esprit.pidevbackend.Domain.Offer;
import com.esprit.pidevbackend.Domain.Publicity;
import lombok.Data;

@Data
public class Com {
    String com;
    String com2;
    public Com(String com){
        super();
        this.com= com;
    }
    public Com(String com, String com2){
        super();
        this.com= com;

    }


    public Com(Offer addOffer) {

    }

    public Com(Publicity addPublicity) {
    }
}
