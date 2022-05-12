package com.esprit.pidevbackend.Web;

import com.esprit.pidevbackend.Domain.Publicity;
import com.esprit.pidevbackend.Service.CollaborationService;
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
@RequestMapping("/Publicity")
@AllArgsConstructor
public class publicitycontroller {

    private CollaborationService publicityService;

    @Autowired
    ServletContext context;

    //@Secured({"ROLE_ADMIN"})
    //http://localhost:8085/addPublicity/1
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addPublicity")
    @ResponseBody
       public Com addPublicity(@RequestParam("file") MultipartFile file, @RequestParam("body") String publicity) throws JsonParseException, JsonMappingException, Exception {
            Publicity LL = new ObjectMapper().readValue(publicity, Publicity.class);

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

            return new Com(publicityService.addPublicity(LL));
        }

    //@Secured({"ROLE_USER","ROLE_ADMIN"})
    //http://localhost:8080/Collaboration/retrieveAllCollaborations
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/retrieveAllPublicity")
    @ResponseBody
    public List<Publicity> retrieveAllCollaborations() {

        return publicityService.retrieveAllPublicitys();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    //@Secured({"ROLE_USER","ROLE_ADMIN"})
    //http://localhost:8085/Collaboration/retrieveCollaboration
    @GetMapping("/retrievePublicity/{id}")
    @ResponseBody
    public Publicity retrieveCollaboration(@PathVariable Long id){
        return publicityService.retrievePublicity(id);
    }

    //@Secured({"ROLE_ADMIN"})
    //http://localhost:8080/Collaboration/deleteCollaboration/id
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deletePublicity/{id}")
    public void deletePublicity(@PathVariable Long id){
        publicityService.deletePublicity(id);
    }

   // @Secured({"ROLE_ADMIN"})
    //http://localhost:8080/Collaboration/updateCollaboration
   @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updatePublicity")
    @ResponseBody
    public Publicity updatePublicity(@RequestBody Publicity p){
        return publicityService.updatePublicity(p);
    }


    @GetMapping("/Imgarticles2/{id}")
    public byte[] getPhoto(@PathVariable("id") Long idPublicity) throws Exception{

        Publicity p   =publicityService.retrievePublicity(idPublicity);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+p.getPicture()));
    }
}

