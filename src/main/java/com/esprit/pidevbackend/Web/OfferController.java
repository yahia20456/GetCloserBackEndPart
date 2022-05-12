package com.esprit.pidevbackend.Web;

import com.esprit.pidevbackend.Domain.Offer;
import com.esprit.pidevbackend.Service.ICollaborationService;
import com.esprit.pidevbackend.response.Com;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/Offer")
@AllArgsConstructor
public class OfferController {


    private ICollaborationService offerService;

    @Autowired
    ServletContext context;
    //http://localhost:8085/addOffer
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addOffer/{idCollaboration}")
    @ResponseBody

    public Com addOffer(@RequestParam("file") MultipartFile file, @RequestParam("body") String offer,@PathVariable long idCollaboration) throws JsonParseException, JsonMappingException, Exception {
        Offer LL = new ObjectMapper().readValue(offer, Offer.class);

        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        System.out.println(context.getContextPath());
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }


        LL.setPicture(newFileName);

        return new Com(offerService.addOffer(LL,idCollaboration));
    }

    //http://localhost:8085/deleteOffer/id
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteOffer/{id}")
    @ResponseBody
    public void deleteOffer(@PathVariable Long id){
        offerService.deleteOffer(id);
    }

    //http://localhost:8080/Offer/updateOffer
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateOffer")
    @ResponseBody
    public Offer updateOffer(@RequestBody Offer o){
        return offerService.updateOffer(o);
    }

    //http://localhost:8085/Offer/retrieveAllOffers
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retrieveAllOffers")
    @ResponseBody
    public List<Offer> retrieveAllOffers(){
        return offerService.retrieveAllOffers();
    }

    //http://localhost:8085/Offer/retrieveAllOffers/id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retrieveOffer/{id}")
    @ResponseBody
    public Offer retrieveOffer(@PathVariable Long id){
        return offerService.retrieveOffer(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/calculPromotion/{idOffer}")
    @ResponseBody
    public float calculPromotion (@PathVariable long idOffer){
       return offerService.calculProm(idOffer);
    }



    @GetMapping("/Imgarticles1/{id}")
    public byte[] getPhoto(@PathVariable("id") Long idOffer) throws Exception{
        Offer p   =offerService.retrieveOffer(idOffer);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+p.getPicture()));
    }

}




