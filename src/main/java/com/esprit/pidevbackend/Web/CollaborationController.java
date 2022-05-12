package com.esprit.pidevbackend.Web;

import com.esprit.pidevbackend.Domain.Collaboration;
import com.esprit.pidevbackend.Service.ICollaborationService;
import com.esprit.pidevbackend.response.Com;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/Collaboration")

public class CollaborationController {
    @Autowired
	ICollaborationService collaborationService;
	@Autowired
	ServletContext context;



	@CrossOrigin(origins = "http://localhost:4200")
	@Secured({"ROLE_ADMIN"})
	//http://localhost:8085/Collaboration/addCollaboration
	@PostMapping("/addCollaboration")
	public Com addCollaboration(@RequestParam("file") MultipartFile file, @RequestParam("body") String collaboration) throws JsonParseException, JsonMappingException, Exception {
		Collaboration LL = new ObjectMapper().readValue(collaboration, Collaboration.class);

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

		return new Com(collaborationService.addCollaboration(LL));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	//@Secured({"ROLE_ADMIN"})
	//http://localhost:8080/Collaboration/deleteCollaboration/id
	@DeleteMapping("/Collaboration/deleteCollaboration/{id}")
	public void deleteCollaboration(@PathVariable Long id){
		collaborationService.deleteCollaboration(id);
	}

	//@Secured({"ROLE_USER","ROLE_ADMIN"})
	//http://localhost:8080/Collaboration/retrieveAllCollaborations
	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping("/Collaboration/retrieveAllCollaborations")
	@ResponseBody
	public List<Collaboration> retrieveAllCollaborations() {

		return collaborationService.retrieveAllCollaborations();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	//@Secured({"ROLE_ADMIN"})
	//http://localhost:8080/Collaboration/updateCollaboration
	@PutMapping("/Collaboration/updateCollaboration")
	@ResponseBody
	public Collaboration updateCollaboration(@RequestBody Collaboration c){
		return collaborationService.updateCollaboration(c);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	//@Secured({"ROLE_USER","ROLE_ADMIN"})
	//http://localhost:8085/Collaboration/retrieveCollaboration
	@GetMapping("/Collaboration/retrieveCollaboration/{id}")
	@ResponseBody
	public Collaboration retrieveCollaboration(@PathVariable Long id){
		return collaborationService.retrieveCollaboration(id);
	}

	@GetMapping("/Imgarticles/{id}")
	public byte[] getPhoto(@PathVariable("id") Long idCollaboration) throws Exception{

		Collaboration p   =collaborationService.retrieveCollaboration(idCollaboration);
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+p.getPicture()));
	}




}
