package com.example.newprojet.controller;

import com.example.newprojet.model.Client;
import com.example.newprojet.model.Publication;
import com.example.newprojet.service.PublicationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/pub")
@RestController
public class PublicationController {
    @Autowired
    public PublicationServiceInterface publicationServiceInterface;

    @GetMapping("/findAllPublication")
    public List<Publication> findAllAdmin(){
        return publicationServiceInterface.findAllPublication();
    }
    @GetMapping("/findById/{id}")
    public Publication findById(@PathVariable Long id){
        return publicationServiceInterface.findById(id);
    }

    @GetMapping("/findByType/{type}")
    public List<Publication> findByType(@PathVariable String type){
        return publicationServiceInterface.findByType(type);
    }
    @PutMapping ("/update/{id}")
    public Publication update (@PathVariable Long id,@RequestBody Publication client) {
        return publicationServiceInterface.update(id,client);
    }
    @GetMapping("/findByIdc/{id}")
    public List<Publication> findByIdc(@PathVariable Long id){
        return publicationServiceInterface.findByIdc(id);
    }
    @PostMapping("/addPublication")
    public Publication addAdmin(Publication publication, @RequestParam MultipartFile file){
        return publicationServiceInterface.addPublication(publication,file);
    }
    @PostMapping("/addPublicationc/{idc}")
    public Publication addAdmin(Publication publication, @RequestParam MultipartFile file,@PathVariable Long idc){
        return publicationServiceInterface.addPublicationc(publication,file,idc);
    }
    @PostMapping("/addContact/{idpubli}/{idcli}")
    public void addContact(@RequestBody Publication pub ,@PathVariable Long idpubli,@PathVariable Long idcli){
         publicationServiceInterface.addContact(pub,idpubli,idcli);
    }
    @PostMapping("/ContactMy")
    public void ContactMy(@RequestBody Publication pub){
        publicationServiceInterface.ContactMy(pub);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        publicationServiceInterface.deletePublication(id);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        return publicationServiceInterface.getFile(filename);
    }


}
